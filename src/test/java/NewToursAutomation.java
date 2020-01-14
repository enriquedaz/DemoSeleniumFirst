import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewToursAutomation {
    private WebDriver driver;

    By registerLinkLocator = By.linkText("REGISTER");
    By signOnLinkLocator = By.linkText("SIGN-ON");

    By registerImagePageLocator = By.xpath("//img[@src='/images/masts/mast_register.gif']");
    By singOnImagePageLocator = By.xpath("//img[@src='/images/masts/mast_signon.gif']");
    By flightsImageLocator = By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']");

    By usernameLocator = By.id("email");
    By passwordLocator = By.name("password");
    By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");

    By userNameSigOn = By.name("userName");

    // Botones
    By registerButtonLocator = By.name("register");
    By signOnButtonLocator = By.name("login");

    By fontLocator = By.tagName("font");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://newtours.demoaut.com/");
    }
    @Test
    public void registerUser()throws InterruptedException{
        driver.findElement(registerLinkLocator).click();
        Thread.sleep(2000);
        if(driver.findElement(registerImagePageLocator).isDisplayed()){
            driver.findElement(usernameLocator).sendKeys("pecador");
            driver.findElement(passwordLocator).sendKeys("pecas");
            driver.findElement(confirmPasswordLocator).sendKeys("pecas");

            driver.findElement(registerButtonLocator).click();
        }
        else{
            System.out.println("Page Register not found");
        }
        List<WebElement> fonts = driver.findElements(fontLocator);

        assertEquals("Note: Your user name is pecador.",fonts.get(5).getText());

    }

    @Test
    public void sigInPassInvalid()throws InterruptedException{
        driver.findElement(signOnLinkLocator).click();
        Thread.sleep(2000);
        if(driver.findElement(singOnImagePageLocator).isDisplayed()){
            driver.findElement(userNameSigOn).sendKeys("pecador");
            driver.findElement(passwordLocator).sendKeys("pecador");

            driver.findElement(signOnButtonLocator).click();
        }
        else{
            System.out.println("Page Register not found");
        }

        assertTrue(driver.findElement(singOnImagePageLocator).isDisplayed());
    }

    @Test
    public void sigInPassValid()throws InterruptedException{
        driver.findElement(signOnLinkLocator).click();
        Thread.sleep(2000);
        if(driver.findElement(singOnImagePageLocator).isDisplayed()){
            driver.findElement(userNameSigOn).sendKeys("pecador");
            driver.findElement(passwordLocator).sendKeys("pecas");

            driver.findElement(signOnButtonLocator).click();
        }
        else{
            System.out.println("Page Register not found");
        }
        assertTrue(driver.findElement(flightsImageLocator).isDisplayed());
    }

    @After
    public void tearDow(){
        driver.quit();
    }


}
