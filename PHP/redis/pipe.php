<?php
 
$redis = new Redis();
$redis->connect('10.1.132.86', 6379);
 
$pipe = $redis->multi(Redis::PIPELINE);
for ($i = 0; $i <  10000; $i++) {
    $pipe->set("key::$i", str_pad($i, 4, '0', 0));
    $pipe->get("key::$i");
}
$replies = $pipe->exec();
echo "
";
print_r($replies);
