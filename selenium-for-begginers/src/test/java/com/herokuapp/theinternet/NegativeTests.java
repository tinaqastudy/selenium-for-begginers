package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	protected void setup(@Optional("chrome") String browser) {
		// create driver
		System.out.println("creating driver: " + browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"/home/tina/git/repository/selenium-for-begginers/src/main/resources/chromedriver");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"/home/tina/git/repository/selenium-for-begginers/src/main/resources/geckodriver");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Do not know how to start " + browser + ". Starting chrome");
			System.setProperty("webdriver.chrome.driver",
					"/home/tina/git/repository/selenium-for-begginers/src/main/resources/chromedriver");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, groups = { "smokeTests", "negativeTests" })
	public void negativeTest(String username, String password, String expectedMessage) {

		System.out.println("starting negative test");

		// open url
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);

		// enter incorrect username
		WebElement usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys(username);

		// insert correct password
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys(password);

		// push login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();

		// verify error message
		WebElement error_message = driver.findElement(By.id("flash"));

		String actualMessage = error_message.getText();

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual error message does not contain expected message\nexpectedMessage: " + expectedMessage
						+ "\nActual message: " + actualMessage);

	}

	@AfterMethod
	protected void tearDown() {
		System.out.println("Close driver");
		driver.quit();

	}

	/*
	 * @Test(priority = 1, groups = {"smokeTests", "negativeTests"}) public void
	 * incorrectUsernameTest() { //negative scenario //insert incorrect username
	 * //correct pass //verify error message "username" is invalid
	 * 
	 * System.setProperty("webdriver.chrome.driver",
	 * "/home/tina/git/repository/selenium-for-begginers/src/main/resources/chromedriver"
	 * ); WebDriver driver = new ChromeDriver();
	 * driver.manage().window().maximize();
	 * 
	 * //open url String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url);
	 * 
	 * //enter incorrect username String username_id = "username"; String
	 * username_name = "tomcruse"; WebElement username =
	 * driver.findElement(By.id(username_id) ); username.sendKeys(username_name);
	 * 
	 * //insert correct password String password_id = "password"; String pasword_str
	 * = "SuperSecretPassword!"; WebElement password =
	 * driver.findElement(By.id(password_id) ); password.sendKeys(pasword_str);
	 * 
	 * //push login button WebElement loginButton =
	 * driver.findElement(By.className("radius")); loginButton.click();
	 * 
	 * //verify error message WebElement error_message =
	 * driver.findElement(By.id("flash")); String expectedMessage =
	 * "Your username is invalid!"; String actualMessage = error_message.getText();
	 * 
	 * Assert.assertTrue(actualMessage.contains(expectedMessage),
	 * "Actual error message does not contain expected message\nexpectedMessage: " +
	 * expectedMessage + "\nActual message: " + actualMessage);
	 * 
	 * driver.quit();
	 * 
	 * }
	 * 
	 * @Test(priority = 2, groups = { "negativeTests"}) public void
	 * incorrectPasswordTest() { //negative scenario //insert incorrect password
	 * //correct pass //verify error message "password" is invalid
	 * 
	 * System.setProperty("webdriver.gecko.driver",
	 * "/home/tina/git/repository/selenium-for-begginers/src/main/resources/geckodriver"
	 * ); WebDriver driver = new FirefoxDriver();
	 * driver.manage().window().maximize();
	 * 
	 * //open url String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url);
	 * 
	 * //enter incorrect username String username_id = "username"; String
	 * username_name = "tomsmith"; WebElement username =
	 * driver.findElement(By.id(username_id) ); username.sendKeys(username_name);
	 * 
	 * //insert correct password String password_id = "password"; String pasword_str
	 * = "wrongpass"; WebElement password = driver.findElement(By.id(password_id) );
	 * password.sendKeys(pasword_str);
	 * 
	 * //push login button WebElement loginButton =
	 * driver.findElement(By.className("radius")); loginButton.click();
	 * 
	 * //verify error message WebElement error_message =
	 * driver.findElement(By.id("flash")); String expectedMessage =
	 * "Your password is invalid!"; String actualMessage = error_message.getText();
	 * 
	 * Assert.assertTrue(actualMessage.contains(expectedMessage),
	 * "Actual error message does not contain expected message\nexpectedMessage: " +
	 * expectedMessage + "\nActual message: " + actualMessage);
	 * 
	 * driver.quit();
	 * 
	 * }
	 */

}
