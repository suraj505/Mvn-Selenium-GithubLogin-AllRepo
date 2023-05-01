package gh.login;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = setUpDriver();
		
		Login logger = new Login();
		List<String> repoHrefs = logger.githubLogin(driver);
		
		InfoGetter infoGetter = new InfoGetter();
		infoGetter.acquireInfo(repoHrefs);
	}
	
	public static WebDriver setUpDriver () {
		System.setProperty("webdriver.chrome.driver", 
				"..\\..\\..\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-position=-32000,-32000");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
	
}
