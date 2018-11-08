package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
	
	@Test(priority = 1)
	public void incorrectUsernameTest() {
		//negative scenario
		//insert incorrect username
		//correct pass
		//verify error message "username" is invalid
		
		System.setProperty("webdriver.chrome.driver",
				"/home/tina/git/repository/selenium-for-begginers/src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//open url
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		
		//enter incorrect username
		String username_id = "username";
		String username_name = "tomcruse";
		WebElement username = driver.findElement(By.id(username_id) );
		username.sendKeys(username_name);
		
		//insert correct password
		String password_id = "password";
		String pasword_str = "SuperSecretPassword!";
		WebElement password = driver.findElement(By.id(password_id) );
		password.sendKeys(pasword_str);
		
		//push login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();
		
		//verify error message
		WebElement error_message = driver.findElement(By.id("flash"));
		String expectedMessage = "Your username is invalid!";
		String actualMessage = error_message.getText();
		
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual error message does not contain expected message\nexpectedMessage: " + expectedMessage
				+ "\nActual message: " + actualMessage);
		
		driver.quit();
		
	}
	
	@Test(priority = 2)
	public void incorrectPasswordTest() {
		//negative scenario
		//insert incorrect password
		//correct pass
		//verify error message "password" is invalid
		
		System.setProperty("webdriver.gecko.driver",
				"/home/tina/git/repository/selenium-for-begginers/src/main/resources/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		//open url
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		
		//enter incorrect username
		String username_id = "username";
		String username_name = "tomsmith";
		WebElement username = driver.findElement(By.id(username_id) );
		username.sendKeys(username_name);
		
		//insert correct password
		String password_id = "password";
		String pasword_str = "wrongpass";
		WebElement password = driver.findElement(By.id(password_id) );
		password.sendKeys(pasword_str);
		
		//push login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();
		
		//verify error message
		WebElement error_message = driver.findElement(By.id("flash"));
		String expectedMessage = "Your password is invalid!";
		String actualMessage = error_message.getText();
		
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual error message does not contain expected message\nexpectedMessage: " + expectedMessage
				+ "\nActual message: " + actualMessage);
		
		driver.quit();
		
	}

}
