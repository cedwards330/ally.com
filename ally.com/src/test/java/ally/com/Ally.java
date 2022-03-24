package ally.com;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.URL;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Ally {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		URL scripturl =Ally.class.getResource("/axe.min.js");
		System.setProperty("webdriver.chrome.browser", "C:\\Users\\cedwards\\Desktop\\Browser Drivers.chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.w3.org/WAI/demos/bad/before/home.html");
		JSONObject responseJson = new AXE.Builder(driver,scripturl).analyze();
		JSONArray violations = responseJson.getJSONArray("violations");
		
		if(violations.length()==0) {
			System.out.println("no errors");
		}
		else {
			AXE.writeResults("Ally", responseJson);
			Assert.assertTrue(false, AXE.report(violations));
		}		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
