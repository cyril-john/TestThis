package KevinJosephProjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShoppingTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://magento.softwaretestingboard.com/");

		driver.findElement(By.xpath("//body/div[2]/header/div/div/ul/li[2]")).click();
		driver.findElement(By.id("email")).sendKeys("firstname@firstname.com");
		driver.findElement(By.name("login[password]")).sendKeys("Firstname@123");
		driver.findElement(By.name("send")).click();

		Actions action = new Actions(driver);

		WebElement men = driver.findElement(By.id("ui-id-5"));
		action.moveToElement(men);
		WebElement tops = driver.findElement(By.id("ui-id-17"));
		action.moveToElement(tops);
		WebElement jackets = driver.findElement(By.id("ui-id-19"));
		action.moveToElement(jackets).click().build().perform();

		driver.findElement(By.xpath("//*[contains(text(),'Style')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Insulated')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Size')]")).click();
		driver.findElement(By.xpath("//div[1]/div/div/div/a[4]/div")).click();

		List<WebElement> products1 = driver.findElements(By.cssSelector("li[class='item product product-item']"));
		WebElement jacket = products1.stream()
				.filter(product1 -> product1.findElement(By.cssSelector("a[class='product-item-link']")).getText()
						.equals("Typhon Performance Fleece-lined Jacket"))
				.findFirst().orElse(null);
		jacket.findElement(By.cssSelector("div[id='option-label-color-93-item-49']")).click();
		jacket.findElement(By.cssSelector("button[title='Add to Cart']")).click();

		WebElement gear = driver.findElement(By.xpath("//div/nav/ul/li[4]"));
		action.moveToElement(gear).build().perform();
		WebElement fitness = driver.findElement(By.xpath("//*[contains(text(),'Fitness Equipment')]"));
		action.moveToElement(fitness).click().build().perform();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scroll(0,1100)");

		List<WebElement> products2 = driver.findElements(By.cssSelector("li[class='item product product-item']"));
		for (WebElement product2 : products2) {
			WebElement bottle = product2.findElement(By.cssSelector("strong[class='product name product-item-name']"));
			if (bottle.getText().contains("Affirm Water Bottle")) {
				WebElement cart = driver.findElement(
						By.xpath("//div/main/div[3]/div/div[3]/ol/li[11]/div/div/div[3]/div/div/form/button"));
				action.moveToElement(bottle).build().perform();
				cart.click();
				break;
			}
		}

		WebElement men2 = driver.findElement(By.id("ui-id-5"));
		action.moveToElement(men2);
		WebElement bottoms = driver.findElement(By.id("ui-id-18"));
		action.moveToElement(bottoms);
		WebElement shorts1 = driver.findElement(By.id("ui-id-24"));
		action.moveToElement(shorts1).click().build().perform();

		List<WebElement> products3 = driver.findElements(By.cssSelector("li[class='item product product-item']"));
		WebElement shorts = products3.stream()
				.filter(product3 -> product3
						.findElement(By.cssSelector("strong[class='product name product-item-name']")).getText()
						.equals("Lono Yoga Short"))
				.findFirst().orElse(null);
		action.moveToElement(shorts).click().build().perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-size-143-item-177")));
		driver.findElement(By.id("option-label-size-143-item-177")).click();
		driver.findElement(By.id("option-label-color-93-item-52")).click();
		driver.findElement(By.id("qty")).clear();
		driver.findElement(By.id("qty")).sendKeys("3");
		driver.findElement(By.cssSelector("button[title='Add to Cart']")).click();

		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span[class='counter-number']")).click();
		driver.findElement(By.id("top-cart-btn-checkout")).click();
		driver.findElement(By.cssSelector("div[class='title']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='title']")));
		System.out.println(
				"There are " + driver.findElement(By.cssSelector("div[class='title']")).getText() + " :");

		List<WebElement> items = driver.findElements(By.cssSelector("strong[class='product-item-name']"));
		for (WebElement cartItem : items) {
			System.out.println(cartItem.getText());
		}

		driver.findElement(By.xpath("//*[contains(text(),'Next')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),'Place Order')]")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("//*[contains(text(),'Your order number is:')]")).getText());
		driver.findElement(By.xpath("//*[contains(text(),'Continue Shopping')]")).click();
		driver.quit();

	}

}
