package pageobjects.common;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.google.common.base.Function;

/**
 * This Class is a Page Base for the other Classes.
 */

public class PageBase {

	//private static int DRIVER_WAIT = 120; 
	private int driverDefaultFluentWait = 240;
	protected WebDriver driver;
	protected static String loggedInAs ="";
	public static String[] defaultLogins;

	@FindBy(className="modal-close")
	public WebElement popupScreen;


	public PageBase(WebDriver driver)
	{
			PageFactory.initElements(driver, this);	
			this.driver = driver;
	}
	

	public WebElement fluentWait(final By selector , WebDriver driver)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(driverDefaultFluentWait, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver)
			{
				return driver.findElement(selector);
			}
		});
	}

	public void waitForElementToBeVisible(By selector ,  WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver,driverDefaultFluentWait);
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}

	public void waitForElementToBeClickable(WebElement element ,  WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver,driverDefaultFluentWait);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementToBeVisibleWithSpecificText(By selector ,  WebDriver driver , String Text)
    {
        WebDriverWait wait = new WebDriverWait(driver,driverDefaultFluentWait);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(selector,Text ));
    }
	
	public void waitForJQuery(WebDriver driver) 
	{
	    (new WebDriverWait(driver, 10)).until((new ExpectedCondition<Boolean>() 
	    {
	        public Boolean apply(WebDriver d) {
	            JavascriptExecutor js = (JavascriptExecutor) d;
	            return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
	        }
	    }));
	}

	/*public void waitForAjax(WebDriver driver) 
	{ //Waits for spinner in grids to be invisible 
	    (new WebDriverWait(driver, 10)).until((new ExpectedCondition<Boolean>() 
	    {
	        public Boolean apply(WebDriver d) {
	            JavascriptExecutor js = (JavascriptExecutor) d;
	            boolean isAjaxFinished = (boolean) js.executeScript("return jQuery.active == 0");
	            boolean isLoaderHidden = (boolean) js.executeScript("return $('.intercom-sheet-spinner').is(':visible') == false");
	                return isAjaxFinished && isLoaderHidden;
	        }
	    }));
	}*/
	
	public boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	public void changeVisibilityOfElementToBlock(String item){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('"+item+"').style.display='block'");
	}
	
}