package interfaceTest.get;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpGetTest {
	@BeforeClass
	public void beforeClass() {
		System.out.println("=======This is BeforeClass=======");
	}

	@DataProvider(name = "user")
	public Object[][] Users() {
		return new Object[][] { { "bb801ee6b9983c7f893473fd6a313ad2", "1", "0" },
				{ "bb801ee6b9983c7f893473fd6a313ad2", "1,2", "5008" },
				{ "bb801ee6b9983c7f893473fd6a313ad2", "123", "0" },
				{ "bb801ee6b9983c7f893473fd6a313ad2", "", "5008" } };

	}

	@Test(dataProvider = "user")
	public void getUri(String access_token, String subject_id, String hcode) throws URISyntaxException, IOException {
		/**
		 * 拼接接口链接
		 */
		URI uri = new URIBuilder().setScheme("http").setHost("gateway.zitech.com/gw/oauthentry")
				.setPath("/study.scene/1.0/getbysid").setParameter("access_token", access_token)
				.setParameter("subject_id", subject_id).build();
		System.out.println(uri);
		/**
		 * 创建httpclient
		 */
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		/**
		 * 设置超时时间
		 */
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectionRequestTimeout(2000)
				.build();
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		System.out.println("响应码:" + response.getStatusLine().getStatusCode());
		/**
		 * 获取服务器响应内容
		 */
		String returnStr = EntityUtils.toString(response.getEntity());
		// System.out.println("响应内容:" + returnStr);

		/**
		 * 解析json
		 */
		JSONObject jsonObject = JSONObject.fromObject(returnStr);
		// System.out.println(jsonObject.has("message"));
		// System.out.println(jsonObject.getString("message"));
		String code = jsonObject.getString("code");
		String message = jsonObject.getString("message");
		/**
		 * 进行结果验证 注意asset对象字符类型,此处全为String类型
		 */
		if (jsonObject.get("data") != null && code.equals("0")) {

			JSONArray array = jsonObject.getJSONArray("data");
			if (array.size() > 0) {
				JSONObject data = array.getJSONObject(0);
				String su_id = data.getString("subjectId");
				System.out.println("subjectId:" + su_id);
				Assert.assertEquals(subject_id, su_id);

			} else {
				Assert.assertEquals(hcode, code);
				System.out.println(code);
			}
		} else {
			Assert.assertEquals(hcode, code);
		}
		System.out.println("message:" + message);

	}

	@AfterClass
	public void afterClass() {
		System.out.println("This is afterClass");
	}
}
