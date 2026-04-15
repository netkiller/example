package cn.netkiller.aliyun;

import java.net.URL;
import java.util.Date;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.Protocol;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
		String endpoint = "oss-cn-shanghai.aliyuncs.com";
		// 从STS服务获取的临时访问密钥（AccessKey ID和AccessKey Secret）。
		String accessKeyId = "DYmJeLTAI5tm1ZaCEB9nUxAP";
		String accessKeySecret = "QkXusBiLMoMIsW3JJhG0D5NOFBEh5a";
		// 从STS服务获取的安全令牌（SecurityToken）。
		String securityToken = "yourSecurityToken";
		// 填写Bucket名称，例如examplebucket。
		String bucketName = "production";
		// 填写Object完整路径，例如exampleobject.txt。Object完整路径中不能包含Bucket名称。
		String objectName = "exampleobject.txt";

		ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
		clientBuilderConfiguration.setProtocol(Protocol.HTTPS);
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken, clientBuilderConfiguration);
		// 设置签名URL过期时间为3600秒（1小时）。
		Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
		URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
		System.out.println(url);
		System.out.println(url.toString().replace(bucketName + "." + endpoint, "oss.netkiller.cn"));
		// 关闭OSSClient。
		ossClient.shutdown();
	}
}
