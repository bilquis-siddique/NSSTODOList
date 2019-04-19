package com.test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NSSTODOListTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp(){
		
		String os = System.getProperty("os.name").toLowerCase();	
		
		if (os.contains("mac")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		}else{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\chromedriver.exe");				
		}
		driver = new ChromeDriver();//launch chrome
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://localhost/qa-exercise/index.php");
	}
	@Test(priority=1)
	public void NSSTODOListLabelTest(){
		boolean label1Status = driver.findElement(By.xpath("//div[@id='label-first']")).isDisplayed();
		
		if (label1Status){
			System.out.println("NSSTODOList Label is present");
		}
		else{
			System.out.println("NSSTODOList Label is NOT present");
		}
	}
	@Test(priority=2)
	public void NSSTODOListLabelTextTest(){
		String actualNSSTODOListLabel = driver.findElement(By.xpath("//div[@id='label-first']")).getText();
		String expectedNSSTODOListLabel = "NSS-TODO List v 1.1";

		System.out.println(actualNSSTODOListLabel);
		
		if (actualNSSTODOListLabel.equals(expectedNSSTODOListLabel)){
			System.out.println("NSSTODOList Label text has passed");
		}
		else{
			System.out.println("NSSTODOList Label text test has failed");
			}		
	}
	@Test(priority=3)
	public void labelTimePresentTest(){
		boolean labelTimeStatus = driver.findElement(By.xpath("//div[@id='label-time']")).isDisplayed();
		if (labelTimeStatus){
			System.out.println("Label time is present");
		}
		else{
			System.out.println("Label time is NOT present");
		}
	}
	@Test(priority=4)
	public void removeBtnPresentTest(){
		boolean removeBtnStatus = driver.findElement(By.xpath("//input[@value='Remove']")).isDisplayed();
		if (removeBtnStatus){
			System.out.println("Remove button is present");
		}
		else{
			System.out.println("Remove button is NOT present");
		}
	}
	@Test(priority=5)
	public void removeBtnEnabledTest(){
		driver.findElement(By.xpath("//input[@value='Remove']")).click();
		
		String expectedlURL = "http://localhost/qa-exercise/todo.php";
		String actualURL = driver.getCurrentUrl();
			
		if (actualURL.equals(expectedlURL)){
			System.out.println("Remove button click navigates to correct URL");
		}
		else{
			System.out.println("Remove button click navigates to WRONG URL");
		}
	}
	@Test(priority=6)
	public void completeBtnPresentTest(){
		boolean completeBtnStatus = driver.findElement(By.xpath("//input[@value='Complete']")).isDisplayed();
		
		if (completeBtnStatus){
			System.out.println("Complete button is present");
		}
		else{
			System.out.println("Complete button is NOT present");
		}
	}
	@Test(priority=7)
	public void completeBtnEnabledTest(){
		driver.findElement(By.xpath("//input[@value='Remove']")).click();
		
		String expectedlURL = "http://localhost/qa-exercise/todo.php";
		String actualURL = driver.getCurrentUrl();
			
		if (actualURL.equals(expectedlURL)){
			System.out.println("Complete button click navigates to correct URL");
		}
		else{
			System.out.println("Complete button click navigates to WRONG URL");
		}
	}
	@Test(priority=8)
	public void toggleAllChkbxPresentTest(){
		boolean toggleChkbxStatus = driver.findElement(By.xpath("//input[@name='allbox']")).isDisplayed();
		
		if (toggleChkbxStatus){
			System.out.println("Toggle checkbox is present");
		}
		else{
			System.out.println("Toggle checkbox is NOT present");
		}
	}
	@Test(priority=9)
	public void allCheckboxesToggledTest(){
		
		//Click "Toggle All" checkbox to verify that all the below checkboxes are selected.
		
		driver.findElement(By.xpath("//input[@name='allbox']")).click();
		boolean CKBToggledStatus = driver.findElement(By.xpath("//input[@name='allbox']")).isSelected();
		
		if (CKBToggledStatus){
			
			List<WebElement> element = driver.findElements(By.xpath("//*[contains(@name,'todo')]"));
			
			for (int i=0;i<element.size();i++){
				if (element.get(i).isSelected()){
					System.out.println(element.get(i) + " checkbox is selected.");
				}
			}
		}
	}
	@Test(priority=10)
	public void advancedLinkPresentTest(){
		try{
			driver.findElement(By.linkText("[Advanced]"));
			System.out.println("Advance link exists");
		}
		catch(NoSuchElementException e){
			System.out.println(e);
		}		
	}
	@Test(priority=11)
	public void advancedLinkToggleCollapseTest(){
		
		//If Advanced link is clicked, the below element will not be displayed/collapse.
					
		driver.findElement(By.linkText("[Advanced]")).click();
			
		boolean selectCatStatus = driver.findElement(By.xpath("//select[@name='category']")).isDisplayed();	
		System.out.println("Category Select element display is " + selectCatStatus);
		
		boolean selectDayStatus = driver.findElement(By.xpath("//select[@name='due_day']")).isDisplayed();	
		System.out.println("Due Day Select element display is " + selectDayStatus);
		
		boolean selectMonthStatus = driver.findElement(By.xpath("//select[@name='due_month']")).isDisplayed();
		System.out.println("Due Day Select element display is " + selectMonthStatus);	
		
		boolean selectYearStatus = driver.findElement(By.xpath("//select[@name='due_year']")).isDisplayed();
		System.out.println("Due Day Select element display is " + selectYearStatus);
		
	}
	@Test(priority=12)
	public void collegeLinkNavigationTest(){
		
		driver.findElement(By.xpath("//a[@href='delcat.php?cid=1']")).click();
		
		String expectedlURL = "http://localhost/qa-exercise/delcat.php?cid=1";
		String actualURL = driver.getCurrentUrl();
			
		if (actualURL.equals(expectedlURL)){
			System.out.println("College Link click navigates to correct URL");
		}
		else{
			System.out.println("College Link click navigates to WRONG URL");
		}		
	}
	@Test(priority=13)
	public void leisureLinkNavigationTest(){
		
		driver.findElement(By.xpath("//a[@href='delcat.php?cid=2']")).click();
		
		String expectedlURL = "http://localhost/qa-exercise/delcat.php?cid=2";
		String actualURL = driver.getCurrentUrl();
			
		if (actualURL.equals(expectedlURL)){
			System.out.println("Leisure Link click navigates to correct URL");
		}
		else{
			System.out.println("Leisure Link click navigates to WRONG URL");
		}		
	}
	@Test(priority=14)
	public void playLinkNavigationTest(){
		
		driver.findElement(By.xpath("//a[@href='delcat.php?cid=3']")).click();
		
		String expectedlURL = "http://localhost/qa-exercise/delcat.php?cid=3";
		String actualURL = driver.getCurrentUrl();
			
		if (actualURL.equals(expectedlURL)){
			System.out.println("Play Link click navigates to correct URL");
		}
		else{
			System.out.println("Play Link click navigates to WRONG URL");
		}		
	}
	@Test(priority=15)
	public void personalLinkNavigationTest(){
		
		driver.findElement(By.xpath("//a[@href='delcat.php?cid=4']")).click();
		
		String expectedlURL = "http://localhost/qa-exercise/delcat.php?cid=4";
		String actualURL = driver.getCurrentUrl();
			
		if (actualURL.equals(expectedlURL)){
			System.out.println("Persona; Link click navigates to correct URL");
		}
		else{
			System.out.println("Personal Link click navigates to WRONG URL");
		}		
	}
	@Test(priority=16)
	public void addBtnTest(){
		/*Adding category functionality with entering value in textbox and 
		 * selecting from dropdowns and then click on ADD button*/

		driver.findElement(By.xpath("//input[@name='data']")).sendKeys("Fiction");
		
		WebElement element1= driver.findElement(By.xpath("//select[@name='category']"));
		Select ddCat = new Select(element1);
		ddCat.selectByValue("2");
		
		WebElement element2= driver.findElement(By.xpath("//select[@name='due_day']"));
		Select ddDueDay = new Select(element2);
		ddDueDay.selectByValue("30");
		
		WebElement element3= driver.findElement(By.xpath("//select[@name='due_month']"));
		Select ddDueMon = new Select(element3);
		ddDueMon.selectByValue("5");
		
		WebElement element4= driver.findElement(By.xpath("//select[@name='due_year']"));
		Select ddYear = new Select(element4);
		ddYear.selectByValue("2019");
		
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		
		String url = driver.getCurrentUrl();
		System.out.println("It navigates to the following error page " + url + 
				" and it doesn't add any category, so test fails");
	}
	@Test(priority=17)
	public void verifyDDListItemTest(){
		
		//Verify the list items
		WebElement element= driver.findElement(By.xpath("//select[@name='due_month']"));
		Select ddDueMon = new Select(element);
		List<WebElement> monthList = ddDueMon.getOptions();
		
		System.out.println("Here are the 12 months");
		for (int i=0;i<monthList.size();i++){
			System.out.println(monthList.get(i).getText());
		}		
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
