<?php

	$dbhost = 'db.example.com';			// 数据库服务器
    $dbuser = 'dbuser';                 // 数据库用户名
    $dbpw = '';                         // 数据库密码
	$dbname = 'dbname';					// 数据库名	
	$database = 'mysql';				// 数据库类型

class Update extends Thread {

    public $name   = '';
    public $running = false;
    public $data = array();
    public function __construct($row) {

        $this->name   = $row['id'];
	$this->data = $row;
        $this->running = true;
    }

    public function run() {
//	global $dbh;
	$row = $this->data;
	$row['mobile'] = safenet_decrypt($row['mobile']);
	$row['id_number'] = safenet_decrypt($row['id_number']);
	$row['email'] = safenet_decrypt($row['email']);	
	if(strlen($row['mobile'])<1 || strlen($row['id_number']) <1 || strlen($row['email']) <1 ){
		$error = sprintf("%s, %s, %s, %s\r\n",$row['id'], $row['mobile'], $row['id_number'], $row['email']);
		file_put_contents("digest_error.log", $error, FILE_APPEND);
	}
	$row['mobile'] = md5($row['mobile']);
	$row['id_number'] = md5($row['id_number']);
	$row['email'] = md5($row['email']); 

	if( strlen($row['mobile']))	printf("update members_digest set mobile = '%s' where id = '%s';\n", $row['mobile'], $row['id']);
	if( strlen($row['id_number']))  printf("update members_digest set id_number = '%s' where id = '%s';\n", $row['id_number'], $row['id']);
	if( strlen($row['email']))      printf("update members_digest set email = '%s' where id = '%s';\n", $row['email'], $row['id']);

	$this->running = false;
    }

}



try {
	$dbh    = new PDO("mysql:host=" . str_replace(':', ';port=', $dbhost) . ";dbname=$dbname", $dbuser, $dbpw, array(
		PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES \'UTF8\'',
		PDO::MYSQL_ATTR_COMPRESS => true
		)
	);
	$sql     = "select id,mobile,id_number,email from members limit 50";
	$row = $dbh->query($sql);
	$pool = array();
	while($member = $row->fetch(PDO::FETCH_ASSOC))
	{
		$id 	= $member['id'];
		while ( true ){
			if(count($pool) < 50){
				$pool[$id] = new Update($member);
				$pool[$id]->start();
				break;
			}else{
				foreach ( $pool as $name => $worker){
					if(! $worker->isRunning()){
						unset($pool[$name]);
/*
			foreach ( $pool as $name => $worker){
				printf("( %s, %s, %s): ", $worker->name, $worker->isRunning(), count($pool));
			}
			print("\r\n");
*/
					}
				}
			}
		}
	}
	$dbh = null;

} catch (Exception $e) {
    echo '【' , date('H:i:s') , '】', '【系统错误】', $e->getMessage(), "\n";
}
?>