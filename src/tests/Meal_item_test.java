package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Meal_item_test extends Basic_test {

	@Test(priority = 1)
	public void addToCart() throws InterruptedException {
		driver.navigate().to(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		Thread.sleep(600);
		mealPage.getFirstProduct().click();
		mealPage.order(4);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Please Select Location"),
				" [ERROR] Select location!");
		notificationSistemPage.getNotification();
		locationPopupPage.openWindow("Beverwyck - Albany");
		Thread.sleep(1500);

		driver.navigate().to(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.getFirstProduct().click();
		mealPage.order(4);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Meal Added To Cart!"),
				" [ERROR] Select meal!");

	}

	@Test(priority = 2)
	public void addMealToFavorite() throws InterruptedException {
		locationPopupPage.closePopup();
		Thread.sleep(600);
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Please login first!"),
				"[ERROR] Login error message did not appear.");
		driver.navigate().to(baseUrl + "guest-user/login-form");
		loginPage.loginAccount("customer@dummyid.com", "12345678a");
		Thread.sleep(500);

		driver.navigate().to(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.getFirstProduct().click();
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Added to Favorites"),
				"[ERROR] Operation added to favorites did not occured.");

	}

	@Test(priority = 3)
	public void clearCart() throws IOException, InterruptedException {
		File file = new File("./data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		driver.navigate().to(baseUrl + "/meals");
		locationPopupPage.getLocationItem("City Center - Albany");

		for (int i = 1; i <= 5; i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();

			Thread.sleep(500);
			driver.get(mealUrl);
			Thread.sleep(1500);
			mealPage.order(quantity);
			Thread.sleep(500);
			sa.assertTrue(notificationSistemPage.getMessageText().contains("Added To Cart "),
					" [ERROR] Unexpected add to cart message" + " for " + mealUrl);
			notificationSistemPage.getNotification();
		}
		sa.assertAll();

		cartSummaryPage.clickOnClear();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("All meals removed from Cart successfully"),
				"[ERROR] Try again!");
		Thread.sleep(1000);

	}

}
