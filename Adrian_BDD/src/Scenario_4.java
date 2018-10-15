import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class Scenario_4 {
	private static WebDriver wd;
	private String url;
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		url = "http://automationpractice.com";
	}
	public static float getPrice(String st)
	{
		float price = 0;
		String p = "";
		for(int i = 0; i < st.length(); i++)
		{
			if(Character.isDigit(st.charAt(i)) || st.charAt(i) =='.')
			{
				p += st.charAt(i);
			} 	
		}
		price = Float.parseFloat(p);
		
		return  price;
	}
	
	
	public static boolean checkPrices(float qty) {
		String cons = wd.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[4]/span/span")).getText();
		String SPriceItem = wd.findElement(By.xpath("//*[@id=\"total_product\"]")).getText();
		String SPriceShipping = wd.findElement(By.xpath("//*[@id=\"total_shipping\"]")).getText();
		String SPriceTotal = wd.findElement(By.xpath("//*[@id=\"total_price\"]")).getText();
		
		float pCons = getPrice(cons);  //sta³a cena
		float pItem = getPrice(SPriceItem);
		float pShipping = getPrice(SPriceShipping);
		float pTotal = getPrice(SPriceTotal);
		
		if ( pCons * qty == pItem || pItem + pShipping == pTotal)
			return true;
		else
			return false;
	}
	@Test
	public void test_buttons() throws InterruptedException {
		wd.get(url);
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		wd.findElement(By.linkText("Blouse")).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.name("Submit")).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wd.findElement(By.partialLinkText("Proceed to checkout")).click();
		
		
		String quanity = wd.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[5]/input[1]")).getAttribute("value").toString();
		
		
		float qty = Float.parseFloat(quanity);
		float qtyOld =qty;
		
		wd.findElement(By.id("cart_quantity_up_2_7_0_0")).click();
		Thread.sleep(500);
		
		quanity = wd.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[5]/input[1]")).getAttribute("value").toString();
		qty = Float.parseFloat(quanity);
		System.out.println(qty + " "+ qtyOld);
		
		Assert.assertTrue(qty == qtyOld + 1.0); //button +
		Assert.assertTrue(checkPrices(qty));
		
		qtyOld = qty;
		wd.findElement(By.xpath("//*[@id=\"cart_quantity_down_2_7_0_0\"]")).click();
		Thread.sleep(1000);
		quanity = wd.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[5]/input[1]")).getAttribute("value").toString();
		qty = Float.parseFloat(quanity);
		
		System.out.println(qty + " "+ qtyOld);
		Assert.assertTrue(qty == (qtyOld - 1)); //button -
		Assert.assertTrue(checkPrices(qty));
	}
	
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
