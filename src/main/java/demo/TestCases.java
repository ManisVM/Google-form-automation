package demo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.util.test.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    @FindBy(xpath ="(//input[@type='text'])[1]")
    WebElement nameTextBox;

    @FindBy(xpath ="(//textarea[@aria-label='Your answer'])[1]")
    WebElement whyTextBox;

    @FindBy(xpath ="(//div[@role=\"radio\"])[1]")
    WebElement experienceRadioBtn;

    @FindBy(xpath ="//div[@data-answer-value='Java']")
    WebElement checkBox1;
    
    @FindBy(xpath ="//div[@data-answer-value='Selenium']")
    WebElement checkBox2;

    @FindBy(xpath ="//div[@data-answer-value='TestNG']")
    WebElement checkBox4;

    @FindBy(xpath ="//span[text()='Choose']")
    WebElement chooseDrop;

    @FindBy(xpath ="(//div[@data-value='Mr'])[2]")
    WebElement MrValue;

    @FindBy(xpath= "//input[@type='date']")
    WebElement date;

    @FindBy(xpath="//div[@role='heading']/span")
    WebElement qaAssignText;

    @FindBy(xpath="(//div[@role='heading']//following::div[@class])[1]")
    WebElement ThanksText;

    @FindBy(xpath="//input[@aria-label='Hour']")
    WebElement hourField;

    @FindBy(xpath="//input[@aria-label='Minute']")
    WebElement minField;

    //span[text()='Submit']
    @FindBy(xpath="//span[text()='Submit']")
    WebElement submit;

    ChromeDriver driver;




    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().browserVersion("125.0.6422.113").setup();
        driver = new ChromeDriver();
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);     
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        TestCases.writeText("Manis", By.xpath("(//input[@type='text'])[1]"), driver);
        TestCases.writeText("To scale up my profession in the same domain for dream job   " + TestCases.calculateEpocheTimetoString(0), By.xpath("(//textarea[@aria-label='Your answer'])[1]"), driver);
        TestCases.clickCheckBox(driver, By.xpath("(//div[@role='radio'])[1]"));
        TestCases.clickCheckBox(driver, By.xpath("//div[@data-answer-value='Java']"));
        TestCases.clickCheckBox(driver, By.xpath("//div[@data-answer-value='Selenium']"));
        TestCases.clickCheckBox(driver, By.xpath("//div[@data-answer-value='TestNG']"));
        Thread.sleep(2000);
        TestCases.clickCheckBox(driver, By.xpath("//span[text()='Choose']"));
        TestCases.clickCheckBox(driver, By.xpath("(//div[@data-value='Mr'])[2]"));
        Thread.sleep(2000);
        TestCases.enterdate(driver, By.xpath("//input[@type='date']"), "dd/MM/yyyy");
        LocalTime currentTime = LocalTime.now();
        String currentTimeString = currentTime.toString();
        System.out.println("Current Time: " + currentTime);
        String[] timeSplit =currentTimeString.split(":");
        hourField.sendKeys(timeSplit[0]);
        minField.sendKeys(timeSplit[1]);
        // driver.get("https://www.amazon.in/");
        submit.click();
        Thread.sleep(3000);
        System.out.println(qaAssignText.getText());
        System.out.println("end Test case: testCase01");
    }


    public static void writeText(String TextToSend, By locator, ChromeDriver driver) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(TextToSend);
        Thread.sleep(2000);
        System.out.println("Success");

    }


    public static void enterdate(ChromeDriver driver, By Locator, String format){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(7);
        System.out.println(previousDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String previousDateString = previousDate.format(formatter);
        System.out.println(previousDateString);
        String[] dateSplit = previousDateString.split("/");
        WebElement element = driver.findElement(Locator);
        for (String value : dateSplit) {
            element.sendKeys(value);
            System.out.println(value);
    
        }
    }

    public static String calculateTimeToString(String formaString, long offsetInMs){

        LocalDateTime TimeNow = LocalDateTime.now();
        Long Seconds = offsetInMs/1000;
        Long Nanos = (offsetInMs % 1000)* 1000000;
        LocalDateTime NewTime = TimeNow.minus(Duration.ofSeconds(Seconds,Nanos));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formaString);
        String FormattedDateAndTime = NewTime.format(formatter);

        return FormattedDateAndTime;

    }

    public static String calculateEpocheTimetoString(int offsetInMs){

        Instant now = Instant.now();
        Instant newInstant = now.plusMillis(offsetInMs);
        long epochMilli = newInstant.toEpochMilli();
        return String.valueOf(epochMilli);
    }

    public static void clickCheckBox(ChromeDriver driver, By Locator){
        System.out.println("Trying to check checkBox");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        WebElement element = driver.findElement(Locator);
        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Actions action= new Actions(driver);
        action.moveToElement(element);
        element.click();

    }
}
