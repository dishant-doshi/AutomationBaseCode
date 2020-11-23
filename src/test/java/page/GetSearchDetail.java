package page;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.Gson;

import base.SetUp;

public class GetSearchDetail extends SetUp {
	By txtNID = By.id("nidVoterInput");
	By txtDOB = By.id("dateOfBirth");
	By btnVerify = By.id("btnVerify");

	By requestId = By.xpath("//*[text()='Request ID']//following-sibling::div[1]");
	By restLocs= By.xpath("//*[@class='col-9 border']");
	String restLocsKey ="(//*[@class='col-9 border'][%s])//preceding-sibling::div[1]";
	String restLocsVal ="(//*[@class='col-9 border'][%s])";
	
	By presentAddress= By.xpath("//*[text()='Present Address']/..//*[@class='col-9'][1]//*[@class='col-3 border']");
	By premententAddress = By.xpath("//*[text()='Permanent Address']/..//*[@class='col-9'][2]//*[@class='col-3 border']");
	
	By verifySuccess = By.xpath("//*[text()='Verification is Successful']");
	By userProfilePhoto;
	By userProfileDetailPhoto = By.id("pt1:i5");
	By btnLogout = By.xpath("//*[text()='Logout']");

	public GetSearchDetail(WebDriver driver) {
		this.driver = driver;
	}

	public void enterNID(String data) {
		sendKeys(txtNID, data);
	}

	public void enterDOB(String data) {
		sendKeys(txtDOB, data);
	}

	public void clickOnVerifyButton() {
		clickOnElement(btnVerify);
	}

	public String getUserDetail(String nid, String dob) {
		enterNID(nid);
		enterDOB(dob);
		clickOnVerifyButton();
		return getUserData();
	}

	public void clickOnLogOut() {
		clickOnElement(btnLogout);
	}
	
	public String getUserData() {
		HashMap<String, Object> restData = new LinkedHashMap<String, Object>();
		HashMap<String, Object> presentAddressData = new LinkedHashMap<String, Object>();
		HashMap<String, Object> premenentAddressData = new LinkedHashMap<String, Object>();
		
		restData.put("requestId", getText(requestId).trim());
		
		List<WebElement> restLocVal = getElementList(restLocs);
		
		for(int i=0 ; i<restLocVal.size() ; i++) {
			String key = getText(By.xpath(String.format(restLocsKey, i)));
			String val = getText(By.xpath(String.format(restLocsVal, i)));
			restData.put(key,val);
		}
		
		List<WebElement> presentAddressLocs = getElementList(presentAddress);
		
		for(int i=0, count = 1; i< presentAddressLocs.size() && count < presentAddressLocs.size()-1 ; i=i+2, count= count +2) {
			presentAddressData.put(presentAddressLocs.get(i).getText().trim(), presentAddressLocs.get(count).getText().trim());
		}
			

		List<WebElement> permenentAddressLocs = getElementList(premententAddress);
		
		for(int i=0, count = 1; i< permenentAddressLocs.size() && count < permenentAddressLocs.size()-1 ; i=i+2, count= count +2) {
			premenentAddressData.put(permenentAddressLocs.get(i).getText().trim(), permenentAddressLocs.get(count).getText().trim());
		}
			
		restData.put("Present Address", presentAddressData);
		restData.put("Permenent Address", permenentAddressLocs);
		
		return new Gson().toJson(restData);
	}
}
