<?php
declare(ticks = 1);

$ini = parse_ini_file('test.ini');
$test = 'aaa';

//信号处理函数
function sig_handler($signo)
{
	global $ini,$test;
     switch ($signo) {
         case SIGTERM:
             // 处理SIGTERM信号
             echo "Caught SIGTERM...\n"; 
             break;
         case SIGHUP:
             //处理SIGHUP信号
			$ini = parse_ini_file('test.ini');
			$test = 'BBBB';
			break;
	     echo "Caught SIGHUP...\n";
             break;
         case SIGUSR1:
             echo "Caught SIGUSR1...\n";
             break;
         default:
             // 处理所有其他信号
     }

}


echo "Installing signal handler...\n";

//安装信号处理器
pcntl_signal(SIGTERM, "sig_handler");
pcntl_signal(SIGHUP,  "sig_handler");
pcntl_signal(SIGUSR1, "sig_handler");

echo "Generating signal SIGTERM to self...\n";

//向当前进程发送SIGUSR1信号
//posix_kill(posix_getpid(), SIGUSR1);

while(true){

	print_r($ini);
	echo $test;
	sleep(1);
}

echo "Done\n"

?> 
