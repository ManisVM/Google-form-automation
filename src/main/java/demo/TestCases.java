package demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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
        nameTextBox.sendKeys("Manis");
        whyTextBox.sendKeys("To scale up my profession in the same domain for dream job");
        experienceRadioBtn.click();
        checkBox1.click();
        checkBox2.click();
        checkBox4.click();
        Thread.sleep(2000);
        chooseDrop.click();
        MrValue.click();
        Thread.sleep(2000);
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(7);
        System.out.println(previousDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String previousDateString = previousDate.format(formatter);
        System.out.println(previousDateString);
        String[] dateSplit = previousDateString.split("/");
        for (String value : dateSplit) {
            date.sendKeys(value);
            System.out.println(value);
    
        }
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


}
