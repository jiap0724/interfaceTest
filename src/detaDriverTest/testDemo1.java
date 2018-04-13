package detaDriverTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testDemo1 {
	// 待测试方法
	public boolean isBetween(int n, int lower, int upper) {

		if (n >= lower && n <= upper) {
			return true;
		} else
			return false;

	}

	// 测试方法
	@Test(dataProvider = "range-provider")
	public void testIsBetween(int n, int lower, int upper, boolean expected) {
		System.out.println("received n " + n + "lower " + lower + "upper " + upper + " expected" + expected);
		Assert.assertEquals(expected, isBetween(n, lower, upper));

	}

	// 测试方法的数据源
	@DataProvider(name = "range-provider")
	public Object[][] rangeData() {

		return new Object[][] { { 5, 100, 10, true } };
	}
}
