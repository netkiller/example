<?php
function __autoload($class_name) {
    require_once('library/'.$class_name . '.php');
}

//include_once('library/Plugin.php');
$plugin = new Plugin();
$plugin->autoload();

//$plugin->load('demo');

echo '=============================';
$plugin->demo->test();
echo '=============================';
$plugin->show();