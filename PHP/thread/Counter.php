<?php
$counter = 0;
//$handle=fopen("php://memory", "rw"); 
//$handle=fopen("php://temp", "rw"); 
$handle=fopen("/tmp/counter.txt", "w"); 
fwrite($handle, $counter ); 
fclose($handle);

class CounterThread extends Thread {
	public function __construct($mutex = null){
		$this->mutex = $mutex;
        $this->handle = fopen("/tmp/counter.txt", "w+");
    }
	public function __destruct(){
		fclose($this->handle);
	}
    public function run() {
		if($this->mutex)
			$locked=Mutex::lock($this->mutex);
		/*
        $this->synchronized(function($thread){
            $thread->wait();
        }, $this);
		*/
		$counter = intval(fgets($this->handle));
		$counter++;
		//$this->lock();
		//fseek($this->handle, 0);
		rewind($this->handle);
		fputs($this->handle, $counter ); 
		//fflush($this->handle);
		//$this->unlock();
		
		printf("Thread #%lu says: %s\n", $this->getThreadId(),$counter);
		if($this->mutex)
			Mutex::unlock($this->mutex);
    }
}

$mutex = Mutex::create(true);
//加入互斥锁
for ($i=0;$i<50;$i++){
	$threads[$i] = new CounterThread($mutex);
	$threads[$i]->start();

}
/*
for ($i=0;$i<100;$i++){
	$threads[$i]->synchronized(function($thread){
		$thread->notify();
	}, $threads[$i]);
}
*/
Mutex::unlock($mutex);
for ($i=0;$i<50;$i++){
	$threads[$i]->join();
}
Mutex::destroy($mutex);
//没有互斥锁
for ($i=0;$i<50;$i++){
	$threads[$i] = new CounterThread();
	$threads[$i]->start();

}

?>