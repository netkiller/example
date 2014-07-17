<?php
interface iPlugin
{
//	public static $author;
//	public static $name;
//	public static $description;
//	public function __construct(){}
	public function set($name, $var);
	public function get($template);
}