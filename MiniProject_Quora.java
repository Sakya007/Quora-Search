package seleniumproject;
import Driver.DriverSetup;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

public class MiniProject_Quora extends DriverSetup{

	String file = System.getProperty("user.dir")+"\\Test_Data\\testdata1.xlsx";
	
	public  void launchUrl() {
		driver.get("https://www.quora.com/profile/Quora");
	}
	
	public void searchBy() throws IOException {
		String searchElement = ExcelUtils.getcelldata(file, "Sheet1", 1, 0);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(searchElement);
		List <WebElement> op = driver.findElements(By.xpath("//div[@class='q-flex qu-alignItems--center qu-py--small qu-overflow--hidden']"));
		op.get(0).click();
		if(driver.findElement(By.xpath("//div[@class='q-box qu-ml--small']/parent::div")).getText().equals("Results for Testing")) {
			System.out.println("Verified");
			ExcelUtils.setcell1(file, "Sheet2", 1, 0, "Verified");
		}
	}
	
	public  void signIn() {
		driver.findElement(By.xpath("//div[text()=\"Sign In\"]")).click();
	}
	
	public  void signUp() {
		driver.findElement(By.xpath("//div[text()=\"Sign up with email\"]")).click();
	}
	
	public  void checkIf() throws IOException {
		if(!(driver.findElement(By.xpath("//button[@tabindex='13']")).isEnabled())) {
			System.out.println("Sign Up button is disabled");
			ExcelUtils.setcelldata(file, "Sheet2", 1, 1, "Sign Up button is disabled");
		}
	}

	public  void errorMessage() throws IOException {
		String email = ExcelUtils.getcelldata(file, "Sheet1", 1, 1);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		String error = driver.findElement(By.xpath("//div[text()='The email address you entered is not valid.']")).getText();
		System.out.println(error);
		ExcelUtils.setcelldata(file, "Sheet2", 1, 2, "The email address you entered is not valid.");
		if(error.equals("The email address you entered is not valid.")) {
			System.out.println("Message verified");
			ExcelUtils.setcelldata(file, "Sheet2", 1, 3, "Message verified");
		}
		else {
			System.out.println("Message not verified");
			ExcelUtils.setcelldata(file, "Sheet2", 1, 3, "Message not verified");
		}
	}
	
	public void captureScreenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\2303535\\eclipse-workspace\\seleniumproject\\screenshots\\error.png");
		FileUtils.copyFile(src, trg);		
	}
	
	public static void main(String[] args) throws InterruptedException, IOException{
		System.out.println("1.Chrome Browser | 2.Edge Browser");
		Scanner sc = new Scanner(System.in);
		MiniProject_Quora mpq=new MiniProject_Quora();
		
		//Launching the browser
		mpq.driverSetup(sc.nextInt());
		Thread.sleep(2000);
		//Opening the url
		mpq.launchUrl();
		Thread.sleep(3000);
		//Searching by the text
		mpq.searchBy();
		Thread.sleep(3000);
		//Signing In
		mpq.signIn();
		Thread.sleep(3000);
		//Signing Up with an Email
		mpq.signUp();
		Thread.sleep(3000);
		//Verify if Sign Up button is disabled
		mpq.checkIf(); 
		Thread.sleep(3000);
		//Error Message shown
		mpq.errorMessage();
		Thread.sleep(1000);
		//Take Screenshot
		mpq.captureScreenshot();
		Thread.sleep(3000);
		//Close the browser
		mpq.closeBrowser();
		sc.close();		
	}
}