import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class Scenario_1  {
		 
		private WebDriver wd;
		private String url;
		
		@Before
		public void setUp()
		{
			wd = new FirefoxDriver();
			url = "http://automationpractice.com";
		}
		
		@Test
		public void Verify_footer() throws InterruptedException {
			wd.get(url);
			Thread.sleep(500);
			WebElement foot = wd.findElement(By.id("footer"));
			
			Assert.assertTrue(foot.findElement(By.className("facebook")).isEnabled());
			Assert.assertTrue(foot.findElement(By.className("twitter")).isEnabled());
			Assert.assertTrue(foot.findElement(By.className("youtube")).isEnabled());
			Assert.assertTrue(foot.findElement(By.className("google-plus")).isEnabled());
			
			 
		}
		
		@After
		public void tearDown() {
			wd.quit();
			
		}
	}


	
