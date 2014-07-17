<?php
final class Plugin{
	private $plugins 	= null;
	private $directory 	= 'plugins';
	private $path		= null;
	public function __construct(){
		$this->path = $this->directory.'/';
	}
	public function autoload(){
		$interfaces = scandir($this->directory);
		unset($interfaces[0]);
		unset($interfaces[1]);
		foreach($interfaces as $interface)
		{
			//load all of the plugins
			$file =  $this->path . $interface;
			if (@file_exists($file))
			{
				include_once($file);
				$class =  basename($interface, ".php");
				if (class_exists($class))
				{
					$this->$class = new $class($this);
					$vars = get_class_vars($class);
					$entity['name'] 			= $vars['name'];
					$entity['description'] 	= $vars['description'];
					$entity['author'] 		= $vars['author'];
					$entity['class'] 		= $class;
					$entity['methods'] 		= get_class_methods($class);
					
					$this->plugins[] = $entity;
				}
			}
		}

	}
	public function load($plugin){
		$file = $this->path . $plugin . '.php';
		if (@file_exists($file))
		{
			include_once($file);
			$class = $plugin;
			if (class_exists($class))
			{
				$this->$class = new $class($this);
				$vars = get_class_vars($class);
				$entity['name'] 			= $vars['name'];
				$entity['description'] 	= $vars['description'];
				$entity['author'] 		= $vars['author'];
				$entity['class'] 		= $class;
				$entity['methods'] 		= get_class_methods($class);

				$this->plugins[] = $entity;
			}
		}
	}
	public function show(){
		print_r($this->plugins);
	}
}