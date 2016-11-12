package pragmatic.bg.QAcomplete6exam;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import junit.framework.Assert;
import tools.WaitTool;

public class ProductCreationTest {

	public static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Pragmatic\\selenium-java browser drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://shop.pragmatic.bg/admin/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void login() {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		usernameField.sendKeys("admin");
		passwordField.sendKeys("parola");

		WebElement loginButton = driver.findElement(By.linkText("Login"));
		loginButton.click();

		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, "Dashboard");
	}

	@Test
	public void createProduct() {
		WebElement CatalogButton = driver.findElement(By.linkText("Catalog"));
		CatalogButton.click();

		WebElement ProductsButton = driver.findElement(By.linkText("Products"));
		ProductsButton.click();

		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, "Products");

		WebElement ProductNameField = driver.findElement(By.name("filter_name"));
		ProductNameField.sendKeys("chris");

		WebElement FilterButton = driver.findElement(By.linkText("Filter"));
		FilterButton.click();
		Boolean exists = driver.findElements(By.xpath("//td[contains(text(),'chris')]")).size() > 0;

		if (exists) {
			WebElement checkbox = driver.findElement(By.xpath("//tr[2]//input[@type='checkbox']"));
			checkbox.click();
			WebElement deleteButton = driver.findElement(By.linkText("Delete"));
			deleteButton.click();
		}

		driver.switchTo().alert().accept();

		WebElement InsertButton = driver.findElement(By.linkText("Insert"));
		InsertButton.click();

		WebElement NewProductNameField = driver.findElement(By.name("product_description[1][name]"));
		NewProductNameField.sendKeys("chris");

		WebElement DataButton = driver.findElement(By.linkText("Data"));
		DataButton.click();

		WebElement ModelField = driver.findElement(By.name("model"));
		ModelField.sendKeys("chris123");

		WebElement SaveButton = driver.findElement(By.linkText("Save"));
		SaveButton.click();

		WebElement successMessage = driver.findElement(By.xpath("//div[@class='success']"));
		String actualSuccessMessage = null;
		actualSuccessMessage = successMessage.getText();
		Assert.assertEquals("Success: You have modified products!", actualSuccessMessage);

	}

}
