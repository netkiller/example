<?php
$tmp = tempnam(__FILE__, 'PHP');
$key = ftok($tmp, 'a');

$shmid = shm_attach($key);
$counter = 0;
shm_put_var( $shmid, 1, $counter );

class MyWorkerThread extends Thread {
	public function __construct($shmid, $mutex = null){
		$this->shmid = $shmid;
		$this->mutex = $mutex;
	}

	public function run(){
		if($this->mutex)
			$locked=Mutex::lock($this->mutex);
	
		$counter = shm_get_var( $this->shmid, 1 ); 
		$counter++;
		shm_put_var( $this->shmid, 1, $counter );		
	
		printf("Thread #%lu lock: %s says: %s\n", $this->getThreadId(), !empty($locked)?"Y":"N", $counter);

		//$this->lock();	

		//$this->unlock();

		if($this->mutex)
			Mutex::unlock($this->mutex);
		return true;
	}
}

$timer = microtime(true);
/* create and lock a mutex */
$mutex = Mutex::create(true);
/* create workers */
$workers = array();
for($i=0;$i<50;$i++){
	$workers[$i]=new MyWorkerThread($shmid,$mutex);
	/* they cannot go anywhere, I have the mutex */
	$workers[$i]->start();
}
printf("Release the (muzzled) hounds ... :\n");
Mutex::unlock($mutex);
foreach($workers as $i=> $worker)
	$workers[$i]->join();
printf("Muzzled: %f seconds\n", microtime(true)-$timer);
/* please remember to destroy mutex and condition variables */
Mutex::destroy($mutex);
shm_put_var( $shmid, 1, 0 );
$timer = microtime(true);
/* same again, no mutex */
printf("Now no mutex ... :\n");
$workers = array();
for($i=0;$i<50;$i++){
	$workers[$i]=new MyWorkerThread($shmid);
	/* they cannot go anywhere, I have the mutex */
	$workers[$i]->start();
}
foreach($workers as $worker)
	$worker->join();
printf("Dribbling: %f seconds\n", microtime(true)-$timer);

shm_remove( $shmid );
shm_detach( $shmid );
?>
