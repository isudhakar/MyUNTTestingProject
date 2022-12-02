package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollSubject {
	private WebDriver driver;
	@SuppressWarnings("unused")
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
	    // setting headless mode to true.. so there isn't any ui
	    //options.setHeadless(true);
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	public void login() {
		WebDriverWait wait = new WebDriverWait(driver, 12);
		driver.get(Properties.url);
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.id("userid")).sendKeys(Properties.username);
		driver.findElement(By.id("pwd")).click();
		driver.findElement(By.id("pwd")).sendKeys(Secrets.password);
		driver.findElement(By.cssSelector(".btn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SCC_PROF_FL_DRV_USERID")));
		assertEquals(driver.findElement(By.id("SCC_PROF_FL_DRV_USERID")).getText().toString(), Properties.rollno);
	}

	@DisplayName("Enroll Subject")
	@Test
	public void enrollNewSubject() {
		login();
		WebDriverWait wait = new WebDriverWait(driver, 30);

		System.out.println(driver.findElement(By.xpath("/html/head/title")).getAttribute("innerHTML"));

		wait.until(ExpectedConditions.elementToBeClickable(By.id("win0divPTNUI_LAND_REC_GROUPLET$2")));
		driver.findElement(By.id("win0divPTNUI_LAND_REC_GROUPLET$2")).click();

		System.out.println("Entered the Enrollment....");

		System.out.println(driver.findElement(By.xpath("/html/head/title")).getAttribute("innerHTML"));

		if (driver.findElement(By.xpath("/html/head/title")).getAttribute("innerHTML").contains("View My Classes")
				|| driver.findElement(By.xpath("/html/head/title")).getAttribute("innerHTML")
						.contains("Manage Classes")) {
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("SCC_LO_FL_WRK_SCC_VIEW_BTN$1")));
			driver.findElement(By.id("SCC_LO_FL_WRK_SCC_VIEW_BTN$1")).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("SSR_CSTRMCUR_GRD$0_row_1")));
			driver.findElement(By.id("SSR_CSTRMCUR_GRD$0_row_1")).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("PTS_KEYWORDS3")));
			driver.findElement(By.id("PTS_KEYWORDS3")).click();
			
			    // 10 | type | id=PTS_KEYWORDS3 | CSCE 5430
			    driver.findElement(By.id("PTS_KEYWORDS3")).sendKeys("CSCE 5430");
			    
			    // 11 | click | id=PTS_SRCH_BTN$IMG | 
			    driver.findElement(By.id("PTS_SRCH_BTN$IMG")).click();
			    
			    //wait.until(ExpectedConditions.elementToBeClickable(By.id("PTS_SELECT$1")));
			    // 12 | click | id=PTS_SELECT$1 | 
			    //driver.findElement(By.id("PTS_SELECT$1")).click();
			    
			   
			    List <WebElement> AllCheckboxes =  driver.findElements(By.id("//input[@type='checkbox']"));
			    
			    int size = AllCheckboxes.size();
			    System.out.println(size);
			    
			    
			    for(int i = 0; i<size; i++) {
			        System.out.println(AllCheckboxes.get(i));
			    	//if(AllCheckboxes.get(i).getText() == "") {
			       // AllCheckboxes.get(i).click();
			    	//}
			        
			    }
			    wait.until(ExpectedConditions.elementToBeClickable(By.id("PTS_SELECT$3")));
			    wait.until(ExpectedConditions.elementToBeClickable(By.id("PTS_SELECT$1")));
			    WebElement es = driver.findElement(By.id("PTS_SELECT$1"));
			    // 13 | click | id=PTS_SELECT$3 | 
			    driver.findElement(By.id("PTS_SELECT$3")).click();
			  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='PTS_SELECT$1']")));
			    // 12 | click | id=PTS_SELECT$1 | 
			    driver.findElement(By.xpath("//input[@id='PTS_SELECT$1']")).click();

			//driver.switchTo().defaultContent();
			//logout();
		} else {
			System.out.println("Not Entered Summary");
		}

	}
	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, 12);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PT_ACTION_MENU$PIMG")));
		driver.findElement(By.id("PT_ACTION_MENU$PIMG")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PT_LOGOUT_MENU")));
		driver.findElement(By.id("PT_LOGOUT_MENU")).click();
		System.out.println("Logout successful!");
	}
}
