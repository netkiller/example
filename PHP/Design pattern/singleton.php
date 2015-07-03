<?php
/*
class Cache {

	private $dbh = null;
	private function __construct(){}
	public query($sql){
		return($sql);
	}

}
*/
class Cache {

	private static $instance;
	private $cache = array();
	private function __construct(){}
	public static function getInstance() {
		if(empty( self::$instance )){
			self::$instance = new Cache();
		}
		return self::$instance;
	}
	public function set($key,$value){
		if(!empty($key)){
			$this->cache[$key] = $value;
		}
	}
	public function get($key){
		if(array_key_exists($key, $this->cache)){
			print($this->cache[$key]);
		}
	}

}

$db = Cache::getInstance();
$db->set('name','netkiller');
$db->get('name');
print("\r\n");

$db1 = Cache::getInstance();
$db1->get('name');
$db1->set('age','30');
print("\r\n");

$db2 = Cache::getInstance();
$db2->get('name');
$db2->get('age');
print("\r\n");

unset($db1);

$db->set('name','neo');
$db->get('age');
$db2->get('name');
print("\r\n");

print("---------------------------\r\n");
// private function __construct(){}
//$db3 = new Cache();
//$db3->set('name','netkiller');

//$db1 = new Cache()
//$db1->get('name');
