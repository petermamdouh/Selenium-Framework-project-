package Test.SkyScanner.net;


import static org.junit.Assert.*;
import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import junit.framework.Assert;


public class ChooseFlight {
	ChromeDriver driver;
	@Before
	public void openURL() {
		String chrome = System.getProperty("user.dir")+"\\recourse\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome);
		driver = new ChromeDriver();
		driver.navigate().to("https://www.skyscanner.net/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement fromTxt = driver.findElement(By.id("fsc-origin-search"));
		fromTxt.click();
		fromTxt.sendKeys("Cairo(CAI)");
		
		WebElement toTxt = driver.findElement(By.id("fsc-destination-search"));
		toTxt.click();
		toTxt.sendKeys("Munich (MUC)");
		
		WebElement departBtn = driver.findElement(By.id("depart-fsc-datepicker-button"));
		departBtn.click();
		
		WebElement optionDe = driver.findElement(By.id("depart-calendar__bpk_calendar_nav_select"));
		Select departOption = new Select(optionDe);
		departOption.selectByIndex(3);
		
		WebElement dayDepartBtn = driver.findElement(By.className("BpkCalendarGrid_bpk-calendar-grid__date__1uO4Y"));
		dayDepartBtn.click();
		
		WebElement returnBtn = driver.findElement(By.id("return-fsc-datepicker-button"));
		returnBtn.click(); 
		
		WebElement optionRe = driver.findElement(By.id("return-calendar__bpk_calendar_nav_select"));
		Select returnOption = new Select(optionRe);
		returnOption.selectByIndex(4);
		
		WebElement dayReturnBtn = driver.findElement(By.className("BpkCalendarGrid_bpk-calendar-grid__date__1uO4Y"));
		dayReturnBtn.click();
		
		WebElement searchBtn = driver.findElement(By.
				xpath("//*[@type='submit']"));
		searchBtn.click();	
	}
	
	@Test
	public void cheapestFlight() {
		WebElement sortBy = driver.findElement(By.xpath("//*[@class='BpkSelect_bpk-select__32bku']"));
		Select sortOption = new Select(sortBy);
		sortOption.selectByIndex(1);
		System.out.println( sortOption.getFirstSelectedOption().getText());
		
		WebElement cheapestTxt = driver.findElement(
			By.xpath("//*[@class='BpkText_bpk-text__2NHsO BpkText_bpk-text--sm__345aT FqsTabs_fqsTabWithSparkleText__mE9wc FqsTabs_fqsTabWithSparkleTextSelected__1T7_o']"));
		String txt = cheapestTxt.getText();
		assertTrue(sortOption.getFirstSelectedOption().getText().contains(txt));
		
	}
	@Test
	public void shortestFlight()  {
		WebElement sortingBy = driver.findElement(By.xpath("//*[@class='BpkSelect_bpk-select__32bku']"));
		Select sortOption = new Select(sortingBy);
		sortOption.selectByIndex(2);
		
		WebElement fasterTxt = driver.findElement( By.
				xpath("//*[@class='BpkText_bpk-text__2NHsO BpkText_bpk-text--sm__345aT FqsTabs_fqsTabWithSparkleText__mE9wc FqsTabs_fqsTabWithSparkleTextSelected__1T7_o']"));
		String txt = fasterTxt.getText();
		assertTrue(sortOption.getFirstSelectedOption().getText().contains(txt));
	}
	
	
	@After
	public void End() {
		driver.quit();
	}

}
