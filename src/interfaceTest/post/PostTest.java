package interfaceTest.post;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * 这是不带参数的post请求
 * 
 * @author jiapeng
 *
 */
public class PostTest {
  @Test
	public void PostDemo() throws Exception, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个post对象
		HttpPost post = new HttpPost(
				"https://health.sinoicity.com/HealthMonitor/open/user/login.htm?deviceId=a617b1fa83e30fb2c29486f5d7c74749&password=e99a18c428cb38d5f260853678922e03&phone=18729399607&os=Android&type=12");
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 获取响应的结果状态码
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		// 获取响应报文 这里注意 要用getentity
		String result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		// 打印到测量报告中
		Reporter.log("请求的URL是：" + post);
		Reporter.log("响应状态码：" + statusCode);
		Reporter.log("响应报文：" + result);
		// 关闭
		response.close();
		httpClient.close();
  }
}
