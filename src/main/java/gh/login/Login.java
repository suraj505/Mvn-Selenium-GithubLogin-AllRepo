package gh.login;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	
	public List<String> githubLogin(WebDriver driver) throws InterruptedException {
		String baseUrl = "https://github.com/login";

		driver.get(baseUrl);
		
		WebElement username = driver.findElement(By.id("login_field"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement signin = driver.findElement(By.cssSelector("input[type='submit']"));

		final String useraccount;
		final String messageuf = "What is your username for GitHub?";
		final JTextField uf = new JTextField();
	    useraccount = JOptionPane.showConfirmDialog( null, uf, messageuf, 
	    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION 
	      ? new String( uf.getText() ) : ""; 
		
		final String userpassword;
		final String messagepf = "What is your password for GitHub?";
	    final JPasswordField pf = new JPasswordField(); 
	    userpassword = JOptionPane.showConfirmDialog( null, pf, messagepf, 
	    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION 
	      ? new String( pf.getPassword() ) : ""; 
		
		username.sendKeys(useraccount);
		password.sendKeys(userpassword);
		signin.click();
		
		Thread.sleep(1000);
		
		try {
		WebElement showMoreButton = driver.findElement(By.xpath("//button[contains(text(),'Show more')]"));
		showMoreButton.click();
		} catch (Exception e) {
			System.out.println("There is no show more button.");
		}
		
		Thread.sleep(1000);
		
		List<WebElement> links = driver.findElements(By.cssSelector("a[href*='" + useraccount + "/']"));
		
		List<String> repoHrefs = new ArrayList<>();
		String followingHref = "https://github.com/" + useraccount + "/following";
		String followersHref = "https://github.com/" + useraccount + "/followers";
		repoHrefs.add(followingHref);
		repoHrefs.add(followersHref);
		for(WebElement a: links) {
			String href = a.getAttribute("href");
			if (!href.contains("following")) {
				repoHrefs.add(href);
			}
		}
		
		driver.close();
		return repoHrefs;
	}
	
}
