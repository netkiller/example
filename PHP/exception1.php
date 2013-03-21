<?php

function test1(){
	throw new Exception("Test1 exception!");
}

try {
	test1();
}
catch(Exception $e){
	die( $e->__toString() );
}

