import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class Scenario_7 extends Create_account_obj {

	private WebDriver wd;
	
	public static String random_email()
	{
		String email = "";
		Random r = new Random();
		for(int i = 0; i < 9; i++)
		{
			int zm = r.nextInt(25)+ 97;
			String z = Character.toString ((char) zm);
			email = email + z;
		}
		for(int i = 0; i < 5; i++)
		{
			int zm = r.nextInt(9)+ 48;
			String z = Character.toString ((char) zm);
			email = email + z;
		}
		email += "@gmail.com";
		return email;
	}
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		
	}
	
	@Test
	public void create_account() throws InterruptedException {
		wd.get(home_page);
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(signIn_link).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(emailCreate).click();
		String email = random_email();
		wd.findElement(emailCreate).sendKeys(email);
		wd.findElement(submitCreate).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Thread.sleep(500);
		wd.findElement(ATitle).click();
		wd.findElement(AFName).sendKeys("John");
		wd.findElement(ALName).sendKeys("Snow");
		wd.findElement(APass).sendKeys("haslohaslo");
		wd.findElement(ADays).sendKeys("1");
		wd.findElement(AMonths).sendKeys("May");
		wd.findElement(AYears).sendKeys("1990");
		wd.findElement(AAdress).sendKeys("Baker Streat 221b");
		wd.findElement(ACity).sendKeys("London");
		new Select (wd.findElement(AState)).selectByVisibleText("Nevada");;
		wd.findElement(AZipCode).sendKeys("00000");
		wd.findElement(Aphone).sendKeys("666666666");
		wd.findElement(subbmitAct).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Assert.assertTrue(wd.getPageSource().contains("Welcome to your account"));
		
		wd.findElement(logout).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Assert.assertTrue(wd.getPageSource().contains("Sign in"));

		wd.findElement(signIn_link).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(AEmail).sendKeys(email);
		wd.findElement(APass).sendKeys("haslohaslo");
		wd.findElement(SignIn).click();
		
		Assert.assertTrue(wd.getPageSource().contains("Welcome to your account"));
		
		Thread.sleep(5000);
		
		 
	}
	
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
