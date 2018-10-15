


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.imageio.ImageIO;
import javax.swing.plaf.FileChooserUI;

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

import junit.framework.Assert;

public class Scenario_6 extends Object_try {
	 
	private WebDriver wd;
	List<String> tittle = new ArrayList();
	List<String> prices = new ArrayList();
	
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		
	}
	
	@Test
	public void Log() throws InterruptedException, IOException {
	wd.get(home_page);
	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	wd.findElement(bestSellers_link).click();
	
	List<WebElement> list = wd.findElements(By.className("product-name"));
	List<WebElement> l2 = wd.findElements(By.xpath("//*[@itemprop ='price']"));
	
	for (int i = 0; i < list.size(); i++)
	{
		if(list.get(i).getText().length() > 1) tittle.add(list.get(i).getText());
	}																							// Do poprawy
	for (int i = 0; i < l2.size(); i++)
	{
		if(l2.get(i).getText().length() > 1) prices.add(l2.get(i).getText());
	}	
	for (int i = 0; i < tittle.size(); i++)
	{
		System.out.print(tittle.get(i) +'-');
		System.out.println(prices.get(i));
	}
	PrintWriter p = new PrintWriter("E://log.txt");
	
	for (int i = 0; i < tittle.size(); i++)
	{
		p.print(tittle.get(i) +'-');
		p.println(prices.get(i));
	}
	System.out.println("saved");
	p.close();
	JavascriptExecutor js = (JavascriptExecutor) wd;
	WebElement offers = wd.findElement(By.id("center_column"));
	js.executeScript("window.scroll (0, " + offers.getLocation().getY() + ") ");
	Thread.sleep(500);
	
	File scr = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
	BufferedImage full = ImageIO.read(scr);
	
	File location = new File("E://screenshot.png");
	FileUtils.copyFile(scr,location);
	
	}
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
