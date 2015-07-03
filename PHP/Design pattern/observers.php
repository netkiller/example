<?php

class Message {

    private $_observers;

    public function __construct() {
        $this->_observers = array();
    }

    public function attach(Sms $observer) {
        return array_push($this->_observers, $observer);
    }

    public function detach(Sms $observer) {
        $index = array_search($observer, $this->_observers);
        if ($index === FALSE || ! array_key_exists($index, $this->_observers)) {
            return FALSE;
        }

        unset($this->_observers[$index]);
        return TRUE;
    }

    public function notifySms() {
        if (!is_array($this->_observers)) {
            return FALSE;
        }

        foreach ($this->_observers as $observer) {
            $observer->send();
        }

        return TRUE;
    }

}

class Sms {

    private $mobile, $message;

    public function __construct($gateway, $mobile, $message) {
	$this->gateway = $gateway;
	$this->mobile 	= $mobile;
	$this->message 	= $message;
    }

    public function send() {
	printf("Send %s, %s, %s\n", $this->gateway, $this->mobile, $this->message);
    }
}

class Client {

    public static function main() {
        $msg = new Message();

        $staff1 = new Sms('Yimei','13113668890','Daytime');
		//$staff1->send('critical','hello world!');
        $msg->attach($staff1);

        $staff2 = new Sms('Diexin','1234567894', 'Morning');
        $msg->attach($staff2);

		$staff3 = new Sms('Yimei','1234567894', 'Evening');
        $msg->attach($staff3);

		$staff4 = new Sms('Yimei','1234567894', 'Night');
        $msg->attach($staff4);
        //$subject->detach($observer1);
        //$msg->notifySms('critical','hello world!!!');
	$msg->notifySms('warning','hello world!!!');
    }
}


class Example {
	/* config */
	const LISTEN = "tcp://192.168.2.15:5555";
	const MAXCONN = 100;
	const pidfile = __CLASS__;
	const uid	= 80;
	const gid	= 80;
	
	protected $pool = NULL;
	protected $zmq = NULL;
	public function __construct() {
		$this->pidfile = '/var/run/'.self::pidfile.'.pid';
	}
	private function daemon(){
		if (file_exists($this->pidfile)) {
			echo "The file $this->pidfile exists.\n";
			exit();
		}
		
		$pid = pcntl_fork();
		if ($pid == -1) {
			 die('could not fork');
		} else if ($pid) {
			 // we are the parent
			 //pcntl_wait($status); //Protect against Zombie children
			exit($pid);
		} else {
			// we are the child
			file_put_contents($this->pidfile, getmypid());
			posix_setuid(self::uid);
			posix_setgid(self::gid);
			return(getmypid());
		}
	}
	private function start(){
		$pid = $this->daemon();
		Client::main();
	}
	private function stop(){

		if (file_exists($this->pidfile)) {
			$pid = file_get_contents($this->pidfile);
			posix_kill($pid, 9); 
			unlink($this->pidfile);
		}
	}
	private function help($proc){
		printf("%s start | stop | help \n", $proc);
	}
	public function main($argv){
		if(count($argv) < 2){
			printf("please input help parameter\n");
			exit();
		}
		if($argv[1] === 'stop'){
			$this->stop();
		}else if($argv[1] === 'start'){
			$this->start();
		}else{
			$this->help($argv[0]);
		}
	}
}

$cgse = new Example();
$cgse->main($argv);

?>
