package testcase;

import org.testng.annotations.Test;

import base.SetUp;

public class TestCase extends SetUp {
	// @Test(dataProvider = "Login", dataProviderClass = TestDataImport.class)
	@Test
	public void getSearchDetail() {
		String result = getSearchDetail.getUserDetail("7333139397", "1977/10/16");
	}

}
