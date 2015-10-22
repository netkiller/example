<?php

function print_line($fd, $events, $arg)
{
    static $pos = 0;
	/*
    if ($max_requests == 10) {
        // exit loop after 10 writes
        event_base_loopexit($arg[1]);
    }*/
    
	
	if(feof($fd)){
		//event_base_loopexit($arg[1]);
		//fseek($fd, $pos);
	}else{
		echo  fgets($fd);
		//$pos = ftell($fd); 
	}
}
 
// create base and event
$base = event_base_new();
$event = event_new();
 
$fd = STDIN;
//$fd = fopen('test.log','r');

// set event flags
event_set($event, $fd, EV_READ | EV_PERSIST, 'print_line', array($event, $base));
// set event base
event_base_set($event, $base);
// enable event
event_add($event);
// start event loop
event_base_loop($base);