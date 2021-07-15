import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;
import org.apache.commons.io.FileUtils;

public class Task1 {

    @Test

    public void verifyGoogle() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);


        try {
            driver.get("https://www.google.com/ncr");
            WebElement webElementImages = driver.findElement(By.xpath("//a[@data-pid=\"2\"]")); // можем ли использовать data-pid?
            webElementImages.click();
            WebElement webElementSearchFieldImages = driver.findElement(By.name("q"));
            webElementSearchFieldImages.sendKeys("cheese" + Keys.ENTER);
            List<WebElement> webElementCheeseImages = driver.findElements(By.xpath("//div[@class=\"islrc\"]//img"));
            for (WebElement webElement : webElementCheeseImages) {
                assertTrue(webElement.isEnabled());
            }

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("D:pageScreenshot.png"), true);

        } finally {
            driver.quit();
        }
    }
}


