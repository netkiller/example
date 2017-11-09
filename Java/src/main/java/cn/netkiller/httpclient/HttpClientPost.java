package cn.netkiller.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientPost {

	public final String url = "http://cms.beta.netkiller.cn:8080/jinDao";
	public final String appId = "3175755150424665";
	public final String appKey = "yEjnjoSEOQpOP49od1IexLkyVB4HTi9c";

	// public final String url = "http://dy.cms.5kwords.com";
	// public final String appId = "1725334758691417";
	// public final String appKey = "1kQMlUaHoNCXv9xGWm3t2DRtdIgg2x6x";

	public String timestamp = null;

	public HttpClientPost() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.timestamp = dateFormat.format(new Date());

	}

	public String token() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String token = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(String.format("%s&%s", this.appKey, this.timestamp).getBytes("UTF-8"))).toLowerCase();
		return token;
	}

	public String restful(String url, List<NameValuePair> params) {
		String responseBody = null;
		String restfulUrl = this.url + url;
		System.out.println(restfulUrl);
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(restfulUrl);
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
			httpPost.setEntity(urlEncodedFormEntity);

			CloseableHttpResponse response = httpclient.execute(httpPost);

			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(entity, "UTF-8");

			System.out.println(responseBody.toString());
			response.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseBody;

	}

	public String contentList() throws NoSuchAlgorithmException, ClientProtocolException, IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appId", appId));
		// params.add(new BasicNameValuePair("contentId", contentId));
		params.add(new BasicNameValuePair("pageSize", "10"));
		params.add(new BasicNameValuePair("timeStamp", timestamp));
		params.add(new BasicNameValuePair("token", this.token()));

		return (this.restful("/api/newsContent/list.jspx", params));
	}

	public void commentList() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String contentId = "1103";

		// // appId=3175755150424665&contentId=1103&pageSize=100&timeStamp=2017-08-03
		// // 10:20:00&token=e1180c0306aff7792c3e25699900dd0d
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appId", appId));
		params.add(new BasicNameValuePair("contentId", contentId));
		params.add(new BasicNameValuePair("pageSize", "10"));
		params.add(new BasicNameValuePair("timeStamp", timestamp));
		params.add(new BasicNameValuePair("token", this.token()));

		this.restful("/api/comment/list.jspx", params);
	}
	public void commentCreate() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String contentId = "1103";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appId", appId));
		params.add(new BasicNameValuePair("siteId", "22"));
		params.add(new BasicNameValuePair("contentId", contentId));
		params.add(new BasicNameValuePair("userId", "1"));
		
		params.add(new BasicNameValuePair("userName", "Neo"));
		params.add(new BasicNameValuePair("userIp", "127.0.0.1"));
		params.add(new BasicNameValuePair("text", "It's a cat."));
		params.add(new BasicNameValuePair("createTime", "2017-10-20"));
		
		params.add(new BasicNameValuePair("nickname", "Neo's iphone"));
		params.add(new BasicNameValuePair("picture", "http://www.test.com/1.jpg"));
		
		params.add(new BasicNameValuePair("timeStamp", timestamp));
		params.add(new BasicNameValuePair("token", this.token()));

		this.restful("/api/comment/add.jspx", params);
	}
	public void finance() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appId", appId));
		params.add(new BasicNameValuePair("timeStamp", timestamp));
		params.add(new BasicNameValuePair("token", this.token()));

		params.add(new BasicNameValuePair("releaseTime", "2017-09-04"));
		// params.add(new BasicNameValuePair("date", "2017-09-04"));
		params.add(new BasicNameValuePair("status", "1"));
		// params.add(new BasicNameValuePair("country", "usa"));
		// params.add(new BasicNameValuePair("kinds", "欧元"));
		params.add(new BasicNameValuePair("importanceLevel", "1"));

		this.restful("/api/finance/list.jspx", params);
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		HttpClientPost test = new HttpClientPost();
		test.commentCreate();
		test.commentList();
		// test.contentList();
		// test.finance();

	}

}
