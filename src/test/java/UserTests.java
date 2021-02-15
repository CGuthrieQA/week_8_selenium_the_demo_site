import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class UserTests {

    private static RemoteWebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void test() {
    	System.out.println("starting test");
    	
    	// GIVEN - that I can access the demo site
    	System.out.println("navigating to site thedemosite.co.uk");
        driver.get("http://thedemosite.co.uk");
        
        // WHEN - I go to the add user page
        System.out.println("clicking the add a user link");
        driver.findElementByXPath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]").click();
        
        // AND - I enter my user credentials
        System.out.println("setting username to guest");
        driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input").sendKeys("guest");
        
        System.out.println("setting password to password");
        driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input").sendKeys("password");
        
        System.out.println("clicking the save button");
        driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input").click();
        
        // AND - I go to the login page
        System.out.println("clicking the login link");
        driver.findElementByXPath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]").click();
        
        // AND - I enter my user credentials
        System.out.println("entering the username guest");
        driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input").sendKeys("guest");
        
        System.out.println("entering the password password");
        driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input").sendKeys("password");
        
        System.out.println("clicking the test login button");
        driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input").click();
        
        // THEN - the login is successful
        System.out.println("checking that the login was successful");
        assertEquals("**Successful Login**", driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b").getText());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
        System.out.println("driver closed");
    }
    
    // Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
    public static ChromeOptions chromeCfg() {
     Map<String, Object> prefs = new HashMap<String, Object>();
     ChromeOptions cOptions = new ChromeOptions();
      
     // Settings
     prefs.put("profile.default_content_setting_values.cookies", 2);
     prefs.put("network.cookie.cookieBehavior", 2);
     prefs.put("profile.block_third_party_cookies", true);

     // Create ChromeOptions to disable Cookies pop-up
     cOptions.setExperimentalOption("prefs", prefs);

     return cOptions;
     }
    
}