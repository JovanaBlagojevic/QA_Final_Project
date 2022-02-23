package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public abstract class Basic_test {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	protected AuthPage authPage;
	protected CartSummaryPage cartSummaryPage;
	protected LoginPage loginPage;
	protected LocationPopupPage locationPopupPage;
	protected MealPage mealPage;
	protected NotificationSistemPage notificationSistemPage;
	protected ProfilePage profilePage;
	SoftAssert sa = new SoftAssert();
	String baseUrl = "http://demo.yo-meals.com/";

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		authPage = new AuthPage(driver, wait, js);
		cartSummaryPage = new CartSummaryPage(driver, wait, js);
		loginPage = new LoginPage(driver, wait, js);
		locationPopupPage = new LocationPopupPage(driver, wait, js);
		mealPage = new MealPage(driver, wait, js);
		notificationSistemPage = new NotificationSistemPage(driver, wait, js);
		profilePage = new ProfilePage(driver, wait, js);
		driver.navigate().to(baseUrl);
		Thread.sleep(500);
		locationPopupPage.closePopup();
		Thread.sleep(1000);
	}

	@Test
	public void test() {
		String username = "customer@dummyid.com";
		String password = "12345678a";

		loginPage.loginAccount(username, password);

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ssa");
		String formattedDate = sdf.format(date);

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(sourceFile, new File("./screenshots/" + formattedDate + ".png"));
				System.out.println("Screenshot taken!");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage() + "!");
			}
		}

		driver.quit();

	}
}