package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Basic_page {
	WebDriver driver;

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}

	public void getLoginButton() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li[2]/a")).click();
	}

	public WebElement getUsername() {
		return driver.findElement(By.name("username"));
	}

	public void clickUsername() {
		driver.findElement(By.name("username")).click();
	}

	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}

	public void clickPassword() {
		driver.findElement(By.name("password")).click();
	}

	public WebElement getCheckbox() {
		return driver.findElement(By.id("remember_me"));
	}

	public WebElement getForgotPassword() {
		return driver.findElement(By.xpath("//*[contains(@text, 'Password')]"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.name("btn_submit"));
	}

	public WebElement getNewUser() {
		return driver.findElement(By.xpath("//*[@id=\"body\"]/section/div/div/div/div[5]"));
	}

	public void loginAccount(String username, String password) {
		getLoginButton();
		getUsername().clear();
		getUsername().sendKeys(username);
		getPassword().clear();
		getPassword().sendKeys(password);
		getSubmit().click();

	}

}
