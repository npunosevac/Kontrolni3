package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
	
	public static final String URL2 = "https://www.saucedemo.com/inventory.html";
	//public static final String PRICE_SORT_XPATH = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select";
	
	public static void clickPriceSort(WebDriver driver) {
		WebElement we = driver.findElement(By.cssSelector("span[class='select_container']"));
		we.click();
	}
	
	public static void clickLowToHigh(WebDriver driver) {
		//otvorili smo dropdown menu i izabrali opciju po indexu
		Select price = new Select (driver.findElement(By.cssSelector("select[class='product_sort_container']")));
		price.selectByIndex(2);
	}
	
}
