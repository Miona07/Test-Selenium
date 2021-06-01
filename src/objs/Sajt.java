package objs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Sajt {

	/*
	 * Napisati program na programskom jeziku Java koji sluzi za testiranje sajta
	 * https://www.saucedemo.com/ Pokusati logovanje prvo sa nevalidnim, a potom sa
	 * validnim kredencijalima i proveriti da li se nakon toga korisnik nalazi na
	 * odgovarajucoj staranici. Na stranici https://www.saucedemo.com/inventory.html
	 * sortirati proizvode po ceni (od najnize ka najvisoj). Proveriti da li je
	 * sortiranje ispravno.
	 */

	public static final String URL = "https://www.saucedemo.com/";

	public static final String USERNAME_XPATH = "//*[@id=\"user-name\"]";

	public static final String PASSOWORD_XPATH = "//*[@id=\"password\"]";

	public static final String LOGIN_URL = "https://www.saucedemo.com/inventory.html";

	public static final String PASSOWORD = "secret_sauce";

	public static void inputUsername(WebDriver driver, String username) {
		WebElement wb = driver.findElement(By.xpath(USERNAME_XPATH));
		wb.click();
		wb.sendKeys(username);

	}

	public static void inputPassword(WebDriver driver, String password) {
		WebElement wb = driver.findElement(By.xpath(PASSOWORD_XPATH));
		wb.click();
		wb.sendKeys(password);

	}

	public static void priceRang(WebDriver driver) {
		// driver.get(LOGIN_URL);
		WebElement wb = driver.findElement(By.className("product_sort_container"));
		wb.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
	}

}
