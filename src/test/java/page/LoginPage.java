package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.SetUp;

public class LoginPage extends SetUp {
	By txtUserName = By.id("login-username");
	By txtPassword = By.id("login-password");
	By btnLogin = By.id("login-button");
	By NID_Voter_Verification = By.xpath("//*[text()='NID/Voter Verification']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String userName) {
		sendKeys(txtUserName, userName);
	}

	public void enterPassword(String password) {
		sendKeys(txtPassword, password);
	}

	public void clickOnLoginButton() {
		clickOnElement(btnLogin);
	}

	public void login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnLoginButton();
		clickOn_NID_VoterVerification();
	}
	
	public void clickOn_NID_VoterVerification() {
		clickOnElement(NID_Voter_Verification);
	}
}
