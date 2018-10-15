import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class Scenario_3 {
	
	private WebDriver wd;
	private String url;
	
	@Before
	public void setUp()
	{
		wd = new FirefoxDriver();
		url = "http://automationpractice.com";
	}
	
	@Test
	public void test_prices() throws InterruptedException {
	wd.get(url);
	Thread.sleep(500);
	
	wd.findElement(By.linkText("Blouse")).click();
	
	Thread.sleep(500);
	String price = (wd.findElement(By.id("our_price_display")).getText());
	wd.findElement(By.name("Submit")).click();
	Thread.sleep(500);
	String price2 = (wd.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[1]/span")).getText());
	String priceShi = wd.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[2]/span")).getText();
	String priceTot = wd.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[3]/span")).getText();
	
	Assert.assertTrue(price.equals(price2)); // item price is correctly displayed
	String priceItem = "";
	String priceShipping = "";
	String priceTotal = "";
	for(int i = 1; i < price2.length(); i++)
	{
		if(Character.isDigit(priceTot.charAt(i)) || priceTot.charAt(i) =='.')
		{
			priceItem += price2.charAt(i);
		} else break;	
	}
	for(int i = 1; i < priceShi.length(); i++)
	{
		if(Character.isDigit(priceTot.charAt(i)) || priceTot.charAt(i) =='.')
		{
			priceShipping += priceShi.charAt(i);
		} else break;	
	}
	
	for(int j =  0; j < priceTot.length(); j++)
	{
		if(Character.isDigit(priceTot.charAt(j)) || priceTot.charAt(j) =='.' )
		{
			System.out.println(priceTot.charAt(j));
			priceTotal += priceTot.charAt(j);
		} 	
	}
	
	float fPriceItem = Float.parseFloat(priceItem);
	float fPriceShipping= Float.parseFloat(priceShipping);
	float fPriceTotal = Float.parseFloat(priceTotal);
	Assert.assertTrue(fPriceItem + fPriceShipping == fPriceTotal); //total price is correctly calculated.
	
	
	
	}
	
	@After
	public void tearDown() {
		wd.quit();
		
	}
}
