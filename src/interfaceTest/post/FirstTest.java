package interfaceTest.post;

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
import org.testng.Reporter;
import org.testng.annotations.Test;

public class FirstTest {
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpPost httppost;
	private HttpResponse response;
	private HttpEntity entity;
	private String postResult = null;

	@Test
	public void loginJDTest() {

		String loginURL = "https://passport.jd.com/uc/loginService?uuid=ac394a05-05c0-4667-9940-2848f0c03809&&r=0.8636558873495089&version=2015";
		// 创建一个httppost请求
		httppost = new HttpPost(loginURL);

		// 创建Post请求参数
		List<NameValuePair> formparams1 = new ArrayList<NameValuePair>();
		formparams1.add(new BasicNameValuePair("uuid", "ac394a05-05c0-4667-9940-2848f0c03809"));
		formparams1.add(new BasicNameValuePair("eid",
				"Z6RZKBBPUUOORPHGELPNEZE6QHFEIE2IS4EQEBWOKH7VJAPDRIYUCSERJ3DLPNXRINL2ON3JC3IWHIG6L6PQMINSRY"));
		formparams1.add(new BasicNameValuePair("fp", "4d981fbd1f0cf45fc497eac85348c0f3"));
		formparams1.add(new BasicNameValuePair("_t", "_nthEAWj"));
		formparams1.add(new BasicNameValuePair("loginType", "c"));
		formparams1.add(new BasicNameValuePair("loginname", "test"));
		formparams1.add(new BasicNameValuePair("nloginpwd", "test"));
		formparams1.add(new BasicNameValuePair("chkRememberMe", ""));
		formparams1.add(new BasicNameValuePair("authcode", ""));
		formparams1.add(new BasicNameValuePair("pubKey", ""));
		formparams1.add(new BasicNameValuePair("sa_token",
				"B68C442BE645754F33277E701208059033541C4BBD4BE7A99A94927BD4C8D683E0AC371429CD887688E05A86C51F7C70CE1F231D2A69583750E3923BA39E17118B83A1A1535BAA4F550CDAFD8E0D42172C6687DE87E4A85EA190DE5B35DA60BB878C558875475CC8D7AC4EA721F380156DD8325B976C3C3266E677EACB204C70C0AE07DF75629F5D845602B5968A00D446F66A103CEE76CED0ED85D144B22580F9A9997B4C6606BBA4ACDA2E5675E1753923A7ED53A8CF7426C57380A1EEB3ACC25E396C9D72455D2F8A2EE727961C86C40637A355F83A8A4AC98AB4DB238EA80705100E51EAD01C3E00868DBD94D782ECF89A937893739D7EB48CD4A388D6DA51AF477822A57E7C2708E31DCEB0ADCA88A95D3FB8D20278B263B38D3679DFCD935E41A9612638671D699F59F0E494BF90B522095FF1E38DDB16541245EDE6703833CD15F1AE6D59575C55942B5306C749927362E5D3D7643BA060F53ACFC46F7222511019EBABBCA226F656DC896C7655A90663537C9BBB13657879FA7B99474D4EC9DB2879B409FCE973FA0A9E4E60F4160C3961B55C3401C7CC402917F6A0984544D666FB2C40B6001E1D740C97DAD646E692403DB4A3191F9C638549616672180317153D3EDC2FA55EA740BC4BAE3F5032FD1E3CE7BDA785DDFD5B65AFD0A4CAC07B18AB8B48FFE65E6C25D8E9D9C6B89D4AC495C2FE7937233EC85C60C8C3EA4A9368697EB136B02766908846FE18CF29CA73A07A0D9C3EA0EEB7A75369"));
		formparams1.add(new BasicNameValuePair("seqSid", "3845068114278942000"));
		// response 转码
		try {
			httppost.setEntity(new UrlEncodedFormEntity(formparams1, "UTF-8"));
			System.out.println("httppost " + httppost);
			response = httpClient.execute(httppost);
			System.out.println("response " + response);
			entity = response.getEntity();
			// 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断创建是否成功
			postResult = EntityUtils.toString(entity, "UTF-8");

			System.out.println("查看登录接口请求返回的结果：" + postResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取响应的结果状态码
		int statusCode = response.getStatusLine().getStatusCode();
		// 报告中打印结果
		Reporter.log("请求的登陆URL是：" + loginURL);
		Reporter.log("接口返回的报文：" + postResult);
		Reporter.log("接口返回状态码：" + statusCode);

		httppost.releaseConnection();
		// 关闭httpclient
		// httpClient.close;
	}
}
