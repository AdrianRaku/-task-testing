

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class Scenario_2 {

	private WebDriver wd;
	private String url;
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		url = "http://google.com";
	}
	
	@Test
	public void testGoogle() throws InterruptedException {
		wd.get(url);
		
		WebElement serachInput = wd.findElement(By.xpath("//*[@id=\"lst-ib\"]"));
		serachInput.clear();
		serachInput.sendKeys("automationpractice.com");
		Thread.sleep(500);
		wd.findElement(By.className("lsb")).click();
		
		List<WebElement> listR = wd.findElements(By.className("r"));
		boolean isAuto = false;
		
		WebElement score = null;
		
		for(WebElement k:listR)
		{
			//k.findElement(By.partialLinkText("automationpractice.com"));
			if (k.findElement(By.tagName("a")).getAttribute("href").toString().equals("http://automationpractice.com/"))
			{
				isAuto = true;
				score = k.findElement(By.tagName("a"));
			}
		}
		Assert.assertTrue(isAuto);
		score.click();
		Thread.sleep(500);
		wd.findElement(By.linkText("Women")).click();
		Thread.sleep(500);
		
		Assert.assertTrue(wd.findElement(By.linkText("Tops")).isEnabled());
		Assert.assertTrue(wd.findElement(By.linkText("Dresses")).isEnabled());
		
	}
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
