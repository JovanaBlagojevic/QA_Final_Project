package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends Basic_page {

	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}

	public WebElement getHeader() {
		return driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[1]/div"));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.id("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));

	}

	public WebElement getLocationInput() {
		return driver.findElement(By.id("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public WebElement getClose() {
		return driver.findElement(By.xpath("//*[contains(@class, 'close-btn-white')]"));
	}

	public void openWindow(String locationValue) {
		getKeyword().click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement locationItem = getLocationItem(locationValue);
		WebElement location = getLocationInput();
		js.executeScript("arguments[0].value=arguments[1]", location, locationItem);
		WebElement submit = getSubmit();
		js.executeScript("arguments[0].click()", submit);
	}

	public void closePopup() {
		getClose().click();
	}
}
