package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends Basic_page {
	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}

	public void profilePage(String firstName, String lastName, String email, String phone, String zipCode,
			String country, String state, String city) {
		this.getUserFirstName().sendKeys(firstName);
		this.getUserLastName().sendKeys(lastName);
		this.getUserEmail().sendKeys(email);
		this.getUserPhone().sendKeys(phone);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByValue(country);
		this.getState().selectByValue(state);
		this.getCity().selectByValue(city);
	}

	public WebElement getClickProfile() {
		return driver.findElement(By.xpath("//*[@id=\"fixed__panel\"]/ul/li[2]"));
	}

	public WebElement getUserRegistration() {
		return driver.findElement(By.xpath("//*[contains(@text, 'User registration')]"));
	}

	public WebElement getUserFirstName() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getUserLastName() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getUserEmail() {
		return driver.findElement(By.name("user_email"));
	}

	public WebElement getUserPhone() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement country = driver.findElement(By.id("user_country_id"));
		Select select = new Select(country);
		return select;
	}

	public Select getState() {
		WebElement state = driver.findElement(By.id("user_state_id"));
		Select select = new Select(state);
		return select;
	}

	public Select getCity() {
		WebElement city = driver.findElement(By.id("user_city"));
		Select select = new Select(city);
		return select;
	}

	public WebElement getSaveButton() {
		return driver.findElement(By.name("btn_submit"));
	}

	public WebElement getCurrentPassword() {
		return driver.findElement(By.name("current_password"));
	}

	public WebElement getNewPassword() {
		return driver.findElement(By.name("new_password"));
	}

	public WebElement getConfirmPassword() {
		return driver.findElement(By.name("conf_new_password"));
	}

	public WebElement getSavePassword() {
		return driver.findElement(By.xpath("//*[@id=\"frm_fat_id_changePwdFrm\"]/div/div[4]/fieldset/input"));
	}

	public WebElement editProfile() {
		return driver.findElement(By.xpath("//*[@id=\"body\"]/div/div/div/div[2]/div/div/div[2]/a[2]"));
	}

	public void uploadImage(String Path) throws InterruptedException {
		File img = new File(Path);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement uploadElement = driver.findElement(By.className("uploadFile-Js"));
		js.executeScript("arguments[0].click()", uploadElement);
		WebElement profilePhotoUpload = driver.findElement(By.name("file"));
		profilePhotoUpload.sendKeys(img.getAbsolutePath());
		Thread.sleep(1000);

	}

	public void removeImage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement removeElement = driver.findElement(By.id("remove"));
		js.executeScript("arguments[0].click()", removeElement);
	}

}
