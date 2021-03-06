package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExceptionsTests {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.out.println("Creating driver");

		System.setProperty("webdriver.chrome.driver",
				"/home/tina/git/repository/selenium-for-begginers/src/main/resources/chromedriver");
		driver = new ChromeDriver();

		sleep(3000);

		driver.manage().window().maximize();
	}

	@Test
	public void notVisibleTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened");

		WebElement startButton = driver.findElement(By.tagName("button"));
		startButton.click();

		WebElement finishText = driver.findElement(By.id("finish"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(finishText));

		Assert.assertTrue(finishText.getText().equals("Hello World!"), "Hello world is not present on the page.");

	}

	@Test
	public void timeoutTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened");

		WebElement startButton = driver.findElement(By.tagName("button"));
		startButton.click();

		WebElement finishText = driver.findElement(By.id("finish"));

		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.visibilityOf(finishText));
		} catch (TimeoutException e) {
			// steps after exception
			System.out.println("Exception catched: " + e.getMessage());
			sleep(3000);
		}

		Assert.assertTrue(finishText.getText().equals("Hello World!"), "Hello world is not present on the page.");

	}

	@Test
	public void noSuchElementTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened");

		WebElement startButton = driver.findElement(By.tagName("button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
				"failed to wait for text to be present in element.");

		// WebElement finishText = driver.findElement(By.id("finish"));

		// Assert.assertTrue(finishText.getText().equals("Hello World!"), "Hello world
		// is not present on the page.");

	}
	
	@Test
	public void staleElementTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_controls";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened");

		WebElement button = driver.findElement(By.xpath("//form[@id='checkbox-example']/button[@type='button']"));
		WebElement checkbox = driver.findElement(By.id("checkbox"));
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		button.click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)), "Checkbox is still displayed, but it shouldn't be.");
		
		//WebElement loading = driver.findElement(By.id("loading"));
		//wait.until(ExpectedConditions.invisibilityOf(loading));
		
		//Assert.assertFalse(checkbox.isDisplayed(), "Checkbox is displayed, but it should not be.");
		
	}

	@AfterMethod
	public void tearDown() {
		sleep(3000);

		System.out.println("Closing driver");
		driver.quit();

	}

	/* Static sleep */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
