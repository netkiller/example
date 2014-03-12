<?php
class Sum extends Stackable {
    private $value = 0;
    public function add($inc)  { $this->value += $inc; }
    public function getValue() { return $this->value; }
    public function run(){}
}

class MyThread extends Thread {
    public $sum;

    public function __construct(Sum $sum) {
        $this->sum = $sum;
    }

    public function run(){
        for ($i=0; $i < 10; $i++) {
            $this->sum->add(5);
            echo $this->sum->getValue() . " ";
        }
    }
}

$sum = new Sum();
$thread = new MyThread($sum);
$thread->start();
$thread->join();
echo $sum->getValue();
?>
