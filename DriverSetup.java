package Driver;


import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties prop;
	
	//To call different browsers
	public void driverSetup(int choice)
	{

		switch(choice) {
		case 1:
			driver=new ChromeDriver();
			break;

		case 2:
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("No browser selected.");
		}

		//To maximize the Browser Window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void closeBrowser()
	{
		driver.quit();
	}
 
}