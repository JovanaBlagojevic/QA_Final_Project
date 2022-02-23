package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Profile_test extends Basic_test {

	@Test(priority = 1)
	public void editProfile() throws InterruptedException {
		driver.navigate().to(baseUrl + "/member/account");
		Thread.sleep(500);
		locationPopupPage.closePopup();
		Thread.sleep(1000);
		String username = "customer@dummyid.com";
		String password = "12345678a";

		loginPage.loginAccount(username, password);
		Thread.sleep(500);
		Assert.assertEquals(notificationSistemPage.getMessageText().contains("Successfull"),
				"[ERROR], incorect parameters! ");

		profilePage.editProfile().click();
		profilePage.profilePage("Jovan", "Rajic", "jovan@gmail.com", "0612341234", "34000", "United States", "AK",
				"Fairbanks");
		Thread.sleep(1000);
		;

		Assert.assertTrue(notificationSistemPage.getErrorMessage().contains("Setup"),
				"[ERROR], Setup message did not appear.");

		notificationSistemPage.getNotification();
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Logout successfully!"),
				"[ERROR], Try again.");
		authPage.getLogout();
	}

	@Test(priority = 2)
	public void changeImageTest() throws InterruptedException {
		driver.navigate().to(baseUrl + "/guest-user/login-form");
		Thread.sleep(500);
		locationPopupPage.closePopup();
		Thread.sleep(1000);

		loginPage.loginAccount("customer@dummyid.com", "12345678a");

		driver.navigate().to(baseUrl + "/member/profile");
		profilePage.uploadImage("img/Jedan.png");

		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Profile Image Uploaded Successfully"),
				"[ERROR] profile image haven't been uploaded.");
		notificationSistemPage.getNotification();
		profilePage.removeImage();
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Deleted"), "[ERROR] try again!");

		notificationSistemPage.getNotification();
		authPage.getLogout();
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Logout successfully"),
				"[ERROR], try again!");

	}

}
