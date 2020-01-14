import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class googleSearchTest {
    private WebDriver driver;
    By videoLinkLocator = By.cssSelector("a[href='https://www.youtube.com/watch?v=RbSlW8jZFe8']");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void testGooglePage() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("QA Automation video");
        searchBox.submit();
        Thread.sleep(3000);
        assertTrue(driver.findElement(videoLinkLocator).isDisplayed());
    }
    @Test
    public void testGooglePageIDE() throws InterruptedException {

        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).sendKeys("qa automation video");
        driver.findElement(By.name("q")).submit();
        assertThat(driver.findElement(By.cssSelector(".bkWMgd > .g > div > .rc .LC20lb")).getText(), is("Automation Testing Tutorial for Beginners - YouTube"));
        driver.close();
    }

    @After
    public void tearDow(){
        driver.quit();
    }
}
