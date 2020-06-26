package TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyNewOrderCreation {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		
		driver.findElement(By.linkText("Order")).click();
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys("" + (int)(1 + (Math.random()*101)));
		
		String middleName = "";
		for (int i=0; i<5; i++) {
			middleName+= (char)((int)(97 + Math.random()*26));
			if (i==0) {
				middleName= middleName.toUpperCase();
			}
		}
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("John " + middleName + " Doe");
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("8607 Westwood Center Dr");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Vienna");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
		
		String zip = "" + (int)(1 + Math.random()*10) + randomNumber(4);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
		
		int card = (int)(Math.random()*3);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_" + card)).click();
		String cardNumber = "";
		if (card == 0) {
			cardNumber += "4" + randomNumber(15);
		}
		if (card == 1) {
			cardNumber += "5" + randomNumber(15);
		}
		if (card == 2) {	
			cardNumber += "3" + randomNumber(14);
		}
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("08/21"); //hard-coded
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		if (driver.getPageSource().contains("New order has been successfully added")) {
			System.out.println("PASS");
		} else {
			System.out.println("FAILED");
		}

	}
	
	public static String randomNumber(int numTimes) {
		String num = "";
		for (int i=0; i<numTimes; i++) {
			num += (int)(Math.random()*10); 
		}
		return num;
	}

}
