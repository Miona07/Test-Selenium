package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objs.Sajt;

public class SajtTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		driver = new ChromeDriver();

	}

	@Test(priority = 1)
	public void invalidLoginTest() {
		driver.get(Sajt.URL);
		Sajt.inputUsername(driver, "Miona");
		Sajt.inputPassword(driver, Sajt.PASSOWORD);
		driver.findElement(By.id("login-button")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String currentUrl = driver.getCurrentUrl();
		String actualUrl = Sajt.URL;

		Assert.assertEquals(currentUrl, actualUrl);

	}

	@Test(priority = 2)
	public void loginTest() {
		driver.get(Sajt.URL);
		Sajt.inputUsername(driver, "standard_user");
		Sajt.inputPassword(driver, Sajt.PASSOWORD);
		driver.findElement(By.id("login-button")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String currentUrl = driver.getCurrentUrl();
		String actualUrl = Sajt.LOGIN_URL;

		Assert.assertEquals(currentUrl, actualUrl);

	}

	@Test(priority = 3)
	public void excelLogin() {
		SoftAssert sa = new SoftAssert();

		File f = new File("data.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			for (int i = 0; i < 4; i++) {
				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);

				String username = c0.toString();

				driver.navigate().to(Sajt.URL);
				Sajt.inputUsername(driver, username);
				Sajt.inputPassword(driver, Sajt.PASSOWORD);
				driver.findElement(By.id("login-button")).click();

				if (username == "performance_glitch_user") {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				String currentUrl = driver.getCurrentUrl();
				String expectedUrl = Sajt.LOGIN_URL;

				if (username == "locked_out_user") {
					sa.assertEquals(currentUrl, Sajt.URL);
				} else {
					sa.assertEquals(currentUrl, expectedUrl);

				}

				sa.assertEquals(currentUrl, expectedUrl);

			}
			sa.assertAll();

			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	public void cenaRastuce() {
		driver.get(Sajt.URL);
		Sajt.inputUsername(driver, "standard_user");
		Sajt.inputPassword(driver, Sajt.PASSOWORD);
		driver.findElement(By.id("login-button")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sajt.priceRang(driver);

		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));

		WebElement lowPrice = driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div"));

		Assert.assertEquals(products.get(0), lowPrice);
	}
}
