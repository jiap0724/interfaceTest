package detaDriverTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FirstTest {
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpPost httppost;
	private HttpResponse response;
	private HttpEntity entity;
	private String postResult = null;

	@Test(dataProvider = "LoginData")
	public void loginJDTest(String caseDescription, String loginURL, String uuid, String eid, String fp, String _t,
			String loginType, String loginname, String nloginpwd, String chkRememberMe, String authcode, String pubKey,
			String sa_token, String seqSid) {

		System.out.println("=====" + caseDescription + "=====");
		// 创建一个httppost请求
		httppost = new HttpPost(loginURL);
		// 创建Post请求参数
		List<NameValuePair> formparams1 = new ArrayList<NameValuePair>();
		formparams1.add(new BasicNameValuePair("uuid", uuid));
		formparams1.add(new BasicNameValuePair("eid", eid));
		formparams1.add(new BasicNameValuePair("fp", fp));
		formparams1.add(new BasicNameValuePair("_t", _t));
		formparams1.add(new BasicNameValuePair("loginType", loginType));
		formparams1.add(new BasicNameValuePair("loginname", loginname));
		formparams1.add(new BasicNameValuePair("nloginpwd", nloginpwd));
		formparams1.add(new BasicNameValuePair("chkRememberMe", chkRememberMe));
		formparams1.add(new BasicNameValuePair("authcode", authcode));
		formparams1.add(new BasicNameValuePair("pubKey", pubKey));
		formparams1.add(new BasicNameValuePair("sa_token", sa_token));
		formparams1.add(new BasicNameValuePair("seqSid", seqSid));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(formparams1, "UTF-8"));
			response = httpClient.execute(httppost);
			entity = response.getEntity();
			// 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断创建是否成功
			postResult = EntityUtils.toString(entity, "UTF-8");

			System.out.println("查看登录接口请求返回的结果：" + postResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		httppost.releaseConnection();
	}


	@DataProvider(name = "LoginData")
	public Object[][] login() throws Exception {
		// 测试数据准备
		String file = "/Users/jiapeng/DeskTop/TestData/LoginTest.xls";
		Object[][] records;
		records = ExcelReader.getExpectationData(file, "login");
		return records;

	}


}
