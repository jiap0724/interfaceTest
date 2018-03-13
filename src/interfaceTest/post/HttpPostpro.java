package interfaceTest.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class HttpPostpro {
	@Test
	public void doPostWithParam() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个post对象
		HttpPost post = new HttpPost("https://health.sinoicity.com/HealthMonitor/open/user/login.htm");
		// 创建一个Entity。模拟一个表单
		List<NameValuePair> List = new ArrayList<>();
		List.add(new BasicNameValuePair("deviceId", "a617b1fa83e30fb2c29486f5d7c74749"));
		List.add(new BasicNameValuePair("password", "e99a18c428cb38d5f260853678922e03"));
		List.add(new BasicNameValuePair("phone", "18729399607"));
		List.add(new BasicNameValuePair("os", "Android"));
		List.add(new BasicNameValuePair("type", "12"));

		// 包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(List, "utf-8");
		// 设置请求的内容
		post.setEntity(entity);
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		Reporter.log("请求的接口url是：" + post);
		Reporter.log("返回结果：" + string);
		response.close();
		httpClient.close();
	}
}
