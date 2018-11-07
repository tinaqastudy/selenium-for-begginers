package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("starting login page");
		// create driver - chrome

		System.setProperty("webdriver.chrome.driver",
				"/home/tina/git/repository/selenium-for-begginers/src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		// open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);

		System.out.println("page is opened");

		// enter username
		// /html//input[@id='username']
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		// push log in button
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();

		// verifications
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		// logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "logoutButton is not displayed");

		// successful log in message
		WebElement successMessage = driver.findElement(By.id("flash"));
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = successMessage.getText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"Actual success message does not contain expected\nexpectedSuccessMessage: " + expectedSuccessMessage
						+ "\nActual message: " + actualSuccessMessage);

		sleep(5000);
		// close browser
		driver.quit();
	}

	/*
	 * static sleep
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
