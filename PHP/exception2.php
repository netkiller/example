<?php

class MyException extends Exception {}
function test2(){
	throw new MyException('Test2 exception');
}
try {
	test2();
}
catch(Exception $e){
	die( $e->__toString() );
}
