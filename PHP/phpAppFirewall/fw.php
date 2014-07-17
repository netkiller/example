<?php
/* 
* =====================================
* Website: http://netkiller.github.com
* Author: neo <netkiller@msn.com>
* Email: netkiller@msn.com
* =====================================
*/
class ApplicationFirewall{

	protected $status;
	protected $policy;
	protected $chain;
	protected $rule;
	protected $match;
	private $debug;
	//$get,$post,$cookie,$server;

	public function __construct() {
		$this->name 	= "ApplicationFirewall";
	}

	public function __destruct() {
		//print "Destroying " . $this->name . "\n";
	}
	
	public function enable(){
		$this->status = true;
	}
	public function disable(){
		$this->status = false;
	}
	
	public function get(){
		if($this->status){
			$this->chain 	= $_GET;
			return($this);
		}else{
			return($this->status);
		}			
	}
	
	public function post(){
		if($this->status){
			$this->chain 	= $_GET;
			return($this);
		}else{
			return($this->status);
		}
		$this->chain 	= $_POST;
	}
	
	public function cookie() {
		if($this->status){
			$this->chain = $_COOKIE;
			return($this);
		}else{
			return($this->status);
		}
		
	}
	
	public function server(){
		if($this->status){
			$this->chain = $_SERVER;
			return($this);
		}else{
			return($this->status);
		}
	}
	
	public function match($key, $value){
		if($this->debug) print_r($this->chain);
		$this->match = false;
		if(!array_key_exists($this->chain, $key)){
			if($this->chain[$key] == $value){
				$this->match = true;	
			}
		}
		return($this);
	}
	public function policy($p){
		$this->policy = $p;
	}
	public function counter($tm, $cnt){
		return($this);
	}
	public function allow($fun = null){
		if($this->status && $this->match){
			if($fun){
				$fun();
			}
		}
		$this->destroy();
		return($this->status);
	}
	public function deny($fun = null){
		if($this->status && $this->match){
			if($fun){
				$fun();
			}
		}
		$this->destroy();
		return($this->status);
	}
	public function debug($tmp){
		$this->debug = $tmp;
	}
	public function ip($ipaddr){
		return $this->server()->match('REMOTE_ADDR', $ipaddr);
	}
	public function destroy(){
		$this->chain = array();
		$this->match = false;
	}
};

#include_once('applicationfirewall.php')
$fw = new ApplicationFirewall();

$fw->debug(true);
$fw->debug(false);
$fw->enable();
//$fw->disable();
function test(){
	echo 'OK';
};
function allow(){
	echo 'allow';
};
function deny(){
	echo 'deny';
};
//$fw->policy('blacklist');

$fw->ip('192.168.3.17')->allow('allow');
$fw->ip('192.168.3.17')->deny('deny');

$fw->counter('1m',5)->match('id','1000')->deny('test');

/*
$fw->ip('172.16.0.0/24')->allow();
$fw->ip('172.16.0.0','255.255.255.0')->allow();

$fw->header(array('User-Agent' => 'MSIE5'))->deny()
*/
$fw->get()->match('id','1000')->deny('test');
$fw->get()->match('name','chen')->allow('test');
//$fw->get()->match(array('id' => '1000'))->deny();
/*
$fw->post()->data(array('action'=>'/login.php'))->allow()
$fw->cookie()->data(array('userid'=>'test'))->deny()
*/
$fw->server()->match('HTTP_REFERER', 'http://www.mydomain.com/index.html')->allow('test');
$fw->server()->match('REQUEST_METHOD', 'GET')->deny('test');

$fw->disable();
//$fw->destroy();