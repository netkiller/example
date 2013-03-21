<?php
class FileException extends Exception {}
class DataException extends Exception {}

function test1(){
	throw new FileException("Test1 exception!");
}

function test2(){
	throw new DataException('Test2 exception');
}
function test3(){
	throw new Exception('Test3 exception');
}

try {
	test1();
	test2();
	test3();
}
catch(FileException $e){
        print( $e->__toString() );
}
catch(DataException $e){
        print( $e->__toString() );
}
catch(Exception $e){
	print( $e->__toString() );
}
