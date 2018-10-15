import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class Scenario_5 {
	 
	private WebDriver wd;
	private String url;
	public static final String home_page = "http://automationpractice.com";
	public static final By email_Input = By.id("email");
	public static final By area_Input = By.id("message");
	public static final By submit_Input = By.id("submitMessage");
	public static final By combobox_Input = By.id("id_contact");
	public static final By file_Input = By.id("fileUpload");
	
	public static String emailPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

	public static boolean isEmail(String email) {
	  Pattern p = Pattern.compile(emailPattern); // Set the email pattern string
	  Matcher m = p.matcher(email); // Match the given string with the pattern
	  return m.matches();
	}
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		url = "http://automationpractice.com";
	}
	
	@Test
	public void test_conact() throws InterruptedException {
		wd.get(url);
		wd.findElement(By.id("contact-link")).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
		
		
		wd.findElement(area_Input).clear();
		wd.findElement(area_Input).sendKeys("blah blah bla");
		wd.findElement(email_Input).sendKeys("emaill@");
		
		wd.findElement(submit_Input).click();
		
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Assert.assertTrue(wd.getPageSource().contains("Invalid email address.")); //veryfi email
		
		wd.navigate().back();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		wd.findElement(email_Input).clear();
		wd.findElement(email_Input).sendKeys("emaill@wp.pl");
		
		WebElement combobox = wd.findElement(By.id("id_contact"));
		Select comboSel = new Select(combobox);
		comboSel.selectByValue("0");
		wd.findElement(By.id("submitMessage")).click();
		Thread.sleep(5000);
		Assert.assertTrue(wd.getPageSource().contains("Please select a subject from the list provided.")); //veryfi combobox Subject 
		
		combobox = wd.findElement(By.id("id_contact"));
		comboSel = new Select(combobox);
		comboSel.selectByValue("2");
		
		wd.findElement(area_Input).clear();
		wd.findElement(area_Input).sendKeys("");
		wd.findElement(submit_Input).click();
		
		Assert.assertTrue(wd.getPageSource().contains("The message cannot be blank.")); //veryfi textarea
		
		wd.findElement(area_Input).clear();
		wd.findElement(area_Input).sendKeys("bla blah blah");
		
		
		wd.findElement(file_Input).clear();
		wd.findElement(file_Input).sendKeys("C:\\kik.png");
		wd.findElement(By.id("submitMessage")).click();
		
		Assert.assertTrue(wd.getPageSource().contains("Your message has been successfully sent to our team."));
		
		Thread.sleep(5000);
	}
	
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
