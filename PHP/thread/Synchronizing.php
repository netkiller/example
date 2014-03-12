<?php
$tmp = tempnam(__FILE__, 'PHP');
$key = ftok($tmp, 'a');

$shmid = shm_attach($key);
$counter = 0;
shm_put_var( $shmid, 1, $counter );

class CounterThread extends Thread {
	public function __construct($shmid){
        $this->shmid = $shmid;
    }
    public function run() {

        $this->synchronized(function($thread){
            $thread->wait();
        }, $this);

		$counter = shm_get_var( $this->shmid, 1 ); 
		$counter++;
		shm_put_var( $this->shmid, 1, $counter );		
		
		printf("Thread #%lu says: %s\n", $this->getThreadId(),$counter);
    }
}

for ($i=0;$i<100;$i++){
	$threads[] = new CounterThread($shmid);
}
for ($i=0;$i<100;$i++){
	$threads[$i]->start();

}

for ($i=0;$i<100;$i++){
	$threads[$i]->synchronized(function($thread){
		$thread->notify();
	}, $threads[$i]);
}

for ($i=0;$i<100;$i++){
	$threads[$i]->join();
}
shm_remove( $shmid );
shm_detach( $shmid );
?>