<?php

interface State
{
    public function handle($state);
    public function display();
}

class Context
{
    private $_state = null;
    public function __construct($state)
    {
        $this->setState($state);
    }

    public function setState($state)
    {
        $this->_state = $state;
    }

    public function request()
    {
        $this->_state->display();
        $this->_state->handle($this);
    }
}
  
class StateA implements State
{
    public function handle($context)
    {
        $context->setState(new StateB());
    }
      
    public function display()
    {
        echo "需求分析\r\n";
    }
}
  
class StateB implements State
{
    public function handle($context)
    {
        $context->setState(new StateC());
    }
  
    public function display()
    {
        echo "产品设计\r\n";
    }
}
  
class StateC implements State
{
    public function handle($context)
    {
        $context->setState(new StateD());
    }
  
    public function display()
    {
        echo "软件开发\r\n";
    }
}

class StateD implements State
{
    public function handle($context)
    {
        $context->setState(new StateA());
    }
  
    public function display()
    {
        echo "软件测试\r\n";
    }
}

$context = new Context(new StateA());
$context->request();
$context->request();
$context->request();
$context->request();

$context->request();
$context->request();
$context->request();
$context->request();

?>
