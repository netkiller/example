<?php
$code = "echo 'Hello!';";
$compressed   = gzdeflate($code, 9);
$compressed = base64_encode($compressed);
echo $compressed;
echo "\r\n";

$compressed = base64_decode($compressed);
$uncompressed = gzinflate($compressed);
echo $uncompressed;
echo "\r\n";

eval($uncompressed);
echo "\r\n";

?>
