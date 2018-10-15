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
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Scenario_9 extends Create_account_obj{
	private WebDriver wd;
	List<String> tittle = new ArrayList();
	List<String> prices = new ArrayList();
	
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		
	}
	public static List getAllId( WebDriver wd)
	{
		List list = new ArrayList();
		
		List<WebElement> probe = wd.findElements(By.xpath("//*[@id]"));
		System.out.println(probe.size());
		for(int i = 0; i < probe.size(); i++)
		{
			System.out.println(probe.get(i).getAttribute("id").toString());
			list.add(probe.get(i).getAttribute("id").toString());
		}
		return list;
	}
	
	@Test
	public void IDs() throws InterruptedException, IOException {
		wd.get(home_page);
		List list = getAllId(wd);
		PrintWriter p = new PrintWriter("E://ids.txt");
		for(int i = 0; i < list.size(); i++)
		{
			p.println(list.get(i));
		}
		System.out.println("saved");
		p.close();
	}
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
