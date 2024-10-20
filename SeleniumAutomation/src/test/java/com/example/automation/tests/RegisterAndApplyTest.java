package com.example.automation.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;


public class RegisterAndApplyTest {

    public static ChromeDriver driver ;
    Faker faker = new Faker();
    String email;
    String password= "P@ssw0rd$$";
 
	    @BeforeMethod
	    public void setup() {
	        // Setup WebDriver
	    	driver = new ChromeDriver();
	 	    driver.manage().window().maximize();	   
	        driver.get("https://mcitcareerssd.elevatus.io/"); 
}
	    
	    @Test
	    public void registerCandidateAndApplyJob() throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    	
	    // Generate dynamic data
	        String firstName = faker.name().firstName();
	        String lastName = faker.name().lastName();
	        email = "suzanarouri384+" + (System.currentTimeMillis() % 10000) + "@gmail.com"; 
	        
	        WebElement cancelCookies = driver.findElement(By.cssSelector(".drawer-handle"));
	        cancelCookies.click();
	        
	    	WebElement registerButton = driver.findElement(By.cssSelector(".is-registration-btn"));
	        registerButton.click();
	       
	        
	    	WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
	        firstNameField.sendKeys(firstName);
	        
	        WebElement lastNameField = driver.findElement(By.name("lastName"));
	        lastNameField.sendKeys(lastName);
	        
	        WebElement emailField = driver.findElement(By.name("email"));
	        emailField.sendKeys(email);
	        
	        WebElement passwordField = driver.findElement(By.name("password"));
	        passwordField.sendKeys(password);
	        
	        WebElement confirmPasswordField = driver.findElement(By.name("confirmPassword"));
	        confirmPasswordField.sendKeys(password);
	 
	        
	        WebElement confirm = driver.findElement(By.cssSelector(".custom-control-label"));
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", confirm);
	        Thread.sleep(500);
	        confirm.click();
	        
	        WebElement registerSubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	        registerSubmitButton.click();
	        Thread.sleep(800);

	   // Verify registration success
	        Assert.assertTrue(driver.getPageSource().contains("Registered successfully"), "Registration failed");

	  // login to the system
	        WebElement login = driver.findElement(By.xpath("//span[contains(.,'Login')]"));
	        login.click();
	      
	        WebElement loginCandidate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login_button']/div[3]/button[3]/div")));
	        loginCandidate.click(); 
	        
	        WebElement loginEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
	        loginEmailField.sendKeys(email);

	        WebElement loginPasswordField = driver.findElement(By.name("password"));
	        loginPasswordField.sendKeys(password);

	        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
	        loginButton.click();

	  // Verify login success        
	        Assert.assertTrue(driver.getPageSource().contains("Logout"), "Login failed");
	    }
	    
	   @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
	

	    }
	    
	    
	    
	  
	    
	    
