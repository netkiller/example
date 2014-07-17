<?php
//function __autoload($class_name) {
//    require_once('library/'.$class_name . '.php');
//}
final class demo implements iPlugin{
	public static $author 		= 'Neo Chen<openunix@163.com>';
	public static $name = 'Demo';
	public static $description = 'Demo Simple';
	public function __construct(){

	}
	public function test(){
		echo 'Hello world!!!';
	}
}