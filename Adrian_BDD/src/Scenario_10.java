import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scenario_10 extends Create_account_obj{

	private WebDriver wd;
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();	
	}
	
	@Test
	public void Log() throws InterruptedException, IOException, AWTException {
		wd.get(home_page);
		Thread.sleep(500);
		Actions action = new Actions(wd);
		wd.findElement(By.className("blockbestsellers")).click();
		WebElement w = wd.findElement(By.linkText("Blouse"));
		WebElement probe = wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[2]/li[3]/div/div[1]/div/a[2]/span"));

		Thread.sleep(500);
		System.out.println(w.getLocation().toString());
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("window.scrollBy(0,500)");
		Actions actions = new Actions(wd);
		actions.moveToElement(w).build().perform();
		WebDriverWait wait = new WebDriverWait(wd, 10);
		wait.until(ExpectedConditions.elementToBeClickable(probe));
		probe.click();
		Thread.sleep(2000);
		
		List<WebElement> thumbs = wd.findElements(By.id("thumbs_list_frame"));
		System.out.println(thumbs.size());
		
		
		
	}
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
