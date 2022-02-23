package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends Basic_page {

	public NotificationSistemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}

	public WebElement getMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success')]"));
	}

	public String getMessageText() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success')]")).getText();
	}

	public String getErrorMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--danger')]")).getText();
	}

	public void getNotification() {
		WebElement locator = driver.findElement(By.xpath("//*[contains(@class, 'system_message')]"));
		wait.until(ExpectedConditions.attributeContains(locator, "style", "display: none;"));
	}

}
