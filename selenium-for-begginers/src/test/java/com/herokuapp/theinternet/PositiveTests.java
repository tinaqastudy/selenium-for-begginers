package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("starting login page");
		//create driver - chrome
		
		System.setProperty("webdriver.chrome.driver", "/home/tina/eclipse-workspace/selenium-for-begginers/src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		sleep(3000);
		driver.manage().window().maximize();
		
		
		//open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		
		sleep(5000);
		
		System.out.println("page is opened");
		
		//enter username
		//enter password
		//push log in button
		//verifications
		//new url
		//logout button is visible
		//successful log in message
		
		//close browser
		driver.quit();
	}

	/* 
	 * static sleep 
	 * */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
