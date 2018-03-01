package interfaceTest.get;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * 这是不带参数的get请求
 * 
 * @author jiapeng
 *
 */
public class GetTest {
  @Test
	public void GetDemo() throws Exception, IOException {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个GET对象
		HttpGet get = new HttpGet("http://api.douban.com/v2/book/search");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 获取响应的结果状态码
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		//响应结果报文转码
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "utf-8");
		System.out.println(result);
		//打印到测量报告中
		Reporter.log("请求的URL是："+get);
		Reporter.log("响应状态码："+statusCode);
		Reporter.log("响应报文：" + result);
		// 关闭httpclient
		response.close();
		httpClient.close();
  }
}
