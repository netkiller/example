<?php
/**
*@name :PHP利用反射机制获取用户自声明的类--反射API
*/
class UserClass{
    public function userMethod($userParameter = 'default'){
        
    }
}
foreach (get_declared_classes() as $class){
    $reflection = new ReflectionClass($class);
    if ($reflection->isUserDefined()) {//判断是否是自声明的类
        Reflection::export($reflection);//打印出该类的一些资料
    }
}
