<?php

$logfile = file('/tmp/access.log');
//print_r($logfile);

foreach ($logfile as $line){
	preg_match('/^(\S+) (\S+) (\S+) \[(.+)\] "([^"]+)" ([0-9]{3}) ([0-9]{3}) "([^"]+)" "([^"]+)" "([^"]+)"/', $line, $matches);
	print_r($matches);
}

?>
