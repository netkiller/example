<?php
error_reporting(E_ALL);
set_time_limit(0);
ob_implicit_flush();

//$host = $_POST['host'];
//$port = $_POST['port'];
//$count = $_POST['count'];

$host = 'www.hx9999.com';
$count = '10000';

$ipaddr = '192.168.2.10';
$port = 80;
$num = 1;

$msg = "GET / HTTP/1.1
User-Agent: Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)
Host: $host
Connection: Keep-Alive
Referer: $host
\r\n";

/* Accept-Encoding: gzip, deflate 

*/

//$msg = "GET /cn/ HTTP/1.1\r\nHost: $host\r\nConnection: Keep-Alive\r\n\r\n";

echo $msg;

while ( $num <= $count ) 
{
	$socket = null;
	if (($socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === false) {
		break;
	}

	if (socket_connect($socket, $ipaddr, $port) === false) {
		break;
	}
	
	socket_write($socket, $msg, strlen($msg));
	/*
	while ($html = socket_read($socket, 2048)) {
		echo $html;
		//break;
	}
	*/
	socket_close($socket);
	$num++;
	print(".");
}
echo "finish: ".$num;