<?php
/* 
* =====================================
* Website: http://netkiller.github.com
* Author: neo <netkiller@msn.com>
* Email: netkiller@msn.com
* =====================================
*/
require 'SharedConfigurations.php';

$single_server = array(
    'host'     => '127.0.0.1',
    'port'     => 6379,
    'database' => 0
);

$multiple_servers = array(
    array(
       'host'     => '127.0.0.1',
       'port'     => 6379,
       'database' => 15,
       'alias'    => 'first',
    ),
    array(
       'host'     => '127.0.0.1',
       'port'     => 6380,
       'database' => 15,
       'alias'    => 'second',
    ),
);


$client = new Predis\Client($single_server, array('prefix' => 'fw:'));

$key=$_SERVER['REMOTE_ADDR'];

if(!$client->exists($key)){
	$client->setex($key, 20, 1);
}else{
	$client->incrby($key,1);
}

$counter = $client->get($key);

if($counter > 10){
	echo 'Deny';
}

print_r($client->get($key));

//var_dump($client->keys('*'));