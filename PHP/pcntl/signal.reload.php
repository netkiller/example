<?php
pcntl_signal(SIGTERM,  function($signo) {
    echo "\n This signal is called. [$signo] \n";
    Status::$state = -1;
});

pcntl_signal(SIGHUP,  function($signo) {
    echo "\n This signal is called. [$signo] \n";
    Status::$state = 1;
	Status::$ini = parse_ini_file('test.ini');
});

class Status{
    public static $state = 0;
	public static $ini = null;
}

$pid = pcntl_fork();
if ($pid == -1) {
    die('could not fork');
}

if($pid) {
    // parent
} else {
	$loop = true;
	Status::$ini = parse_ini_file('test.ini');
    while($loop) {
		print_r(Status::$ini);
        while(true) {
			// Dispatching... 
			pcntl_signal_dispatch();
			if(Status::$state == -1) {
				// Do something and end loop.
				$loop = false;
				break;
			}
			
			if(Status::$state == 1) {
				printf("This program is reload.\r\n");
				Status::$state = 0;
				break;
			}
            echo '.';
            sleep(1);
        }
        echo "\n";
    }
    
    echo "Finish \n";
    exit();
}
