<?php
/* 
* =====================================
* Website: http://netkiller.github.com
* Author: neo <netkiller@msn.com>
* Email: netkiller@msn.com
* =====================================
*/

class Logging {
	protected $file;
	public function __construct($logfile = "/tmp/debug.log"){
		$this->file = fopen($logfile,"a+");
	}
	public function __destruct() {
        //fclose($this->file);
    }
	public function close() {
        fclose($this->file);
    }
	private function write($msg){
			fwrite($this->file,date('Y-m-d H:i:s').' '.$msg."\r\n");
	}
	public function info($msg){
		$this->write(__FUNCTION__.' '.$msg);
	}
	public function warning($msg){
		$this->write(__FUNCTION__.' '.$msg);
	}
	public function error($msg){
		$this->write(__FUNCTION__.' '.$msg);
	}
	public function debug($msg){
		$this->write(__FUNCTION__.' '.$msg);
	}
	
}

class Permission{
	protected $_PERMISSION = array();
	
	public function __construct($login){
		$test = 
		array(
			'neo' => array(
				'News'=> array(
					'add' => 'Y',
					'remove' => 'N',
					'update' => 'Y'
					),
				'RSS'=> array(
					'add' => 'Y',
					'remove' => 'N',
					'update' => 'Y'
					)
				),
			'jam' => array(
				'News'=> array(
					'add' => 'Y',
					'remove' => 'N',
					'update' => 'Y'
					),
				'RSS'=> array(
					'add' => 'Y',
					'remove' => 'N',
					'update' => 'Y'
					)
				)				
		);
		//print_r($test);
		$this->load($test[$login]);
	}
	public function load($arr){
		$this->_PERMISSION = $arr;
	}

	public function is_allowed($class, $fun){
		$class 	= trim($class);
		$fun 	= trim($fun);
		//echo $class, $fun;
		//print_r($this->_PERMISSION);
		if(array_key_exists($class,$this->_PERMISSION)){
			if(array_key_exists($fun,$this->_PERMISSION[$class])){
				if($this->_PERMISSION[$class][$fun] == 'Y') return true;
				//return in_array("Y",$this->_PERMISSION[$class][$fun]);
			}
		}
		return false;
	}
	public function is_denied($class, $fun){
		return (!$this->is_allowed($class, $fun));
	}	
	public function scan(){
		return true;
	}
}

class News extends Permission{

	private $logging;
	public function __construct(){
		parent::__construct('neo');
		$this->logging = new Logging('/tmp/news.log');
	}
	public function __destruct() {
		$this->logging->debug('news->get permission denied!!!');
		$this->logging->close();
    }
	public function add(){
		if(!$this->is_allowed(__CLASS__,__FUNCTION__)) return;
		print("Allowed!!! \r\n");
		$this->logging->info('news->add ok');
	}
	public function get(){
		if( $this->is_denied(__CLASS__,__FUNCTION__)) {
			print("Denied!!! \r\n");
			$this->logging->warning('news->get permission denied!!!');
		}
		
	}
}



$news = new News();
$news->add();
$news->get();
