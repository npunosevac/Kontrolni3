package test;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.HomePage;
import objects.InventoryPage;

public class TestSajta {

	public static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 1)
	public void testLoginNevalidni() {
		File f = new File("data.xlsx");
		try {
			
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);

			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			Row row = sheet.getRow(0);
			Cell c0 = row.getCell(0);

			String username = c0.toString();
			String password = c0.toString();

			driver.navigate().to(HomePage.URL);
			HomePage.typeUsername(driver, username);
			HomePage.typePassword(driver, password);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			HomePage.clickLogin(driver);

			String currentUrl = driver.getCurrentUrl();
			String expectedUrl = HomePage.URL;
			// Assert.assertEquals(currentUrl, expectedUrl);
			sa.assertEquals(currentUrl, expectedUrl);
			sa.assertAll();
			wb.close();
		}

		catch (IOException e) {		
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void testLoginValidni() {
		File f = new File("data.xlsx");
		try {
			
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);

			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();
			Row row = sheet.getRow(1);
			Cell c1 = row.getCell(1);

			String username = c1.toString();
			String password = c1.toString();

			driver.navigate().to(HomePage.URL);
			HomePage.typeUsername(driver, username);
			HomePage.typePassword(driver, password);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			HomePage.clickLogin(driver);

			String currentUrl = driver.getCurrentUrl();
			String expectedUrl = InventoryPage.URL2;
			sa.assertEquals(currentUrl, expectedUrl);
			sa.assertAll();
			wb.close();
		}

		catch (IOException e) {		
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void testPriceRange() {
		
		try {
			File f = new File("data.xlsx");
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);

			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();
			Row row = sheet.getRow(2);
			Cell c2 = row.getCell(2);

			String username = c2.toString();
			String password = c2.toString();
			driver.navigate().to(HomePage.URL);
			HomePage.typeUsername(driver, username);
			HomePage.typePassword(driver, password);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			HomePage.clickLogin(driver);

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// driver.navigate().to(InventoryPage.URL2);
			InventoryPage.clickPriceSort(driver);
			InventoryPage.clickLowToHigh(driver);

			String currentUrl = driver.getCurrentUrl();
			String expectedUrl = InventoryPage.URL2;;
			sa.assertEquals(currentUrl, expectedUrl);
			sa.assertAll();
			wb.close();
		}

		catch (IOException e) {		
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	public void testListSorting() {

		driver.navigate().to(InventoryPage.URL2);
		InventoryPage.clickPriceSort(driver);
		InventoryPage.clickLowToHigh(driver);

		List<WebElement> list = driver.findElements(By.cssSelector("select[class='product_sort_container']"));

		String expectedResult = "nesto";
		String actualResult = list.get(0).getText();

		Assert.assertEquals(expectedResult, actualResult);
	}

}
