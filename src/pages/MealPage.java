package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends Basic_page {
	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getMealsBtn() {
		return driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[1]/div/ul/li[1]/a"));
	}

	public WebElement getQuantity() {
		return driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddToCart() {
		return driver.findElement(By.className("js-proceedtoAddInCart"));
	}

	public WebElement getFirstProduct() {
		return driver.findElement(By.xpath("//*[@id=\"listing\"]/div[1]/div/div[1]/div/div/a"));
	}

	public void orderFirstProduct() {
		getMealsBtn().click();
		js.executeScript("arguments[0].click();", getFirstProduct());
	}

	public WebElement getFavorite() {
		return driver.findElement(By.className("itemfav link"));
	}

	public void order(int quantity) {
		String quant = Integer.toString(quantity);
		getQuantity().clear();
		getQuantity().sendKeys(quant);
		getAddToCart().click();
	}

	public void addToFavorite() {
		js.executeScript("arguments[0].click();", getFavorite());
	}
}
