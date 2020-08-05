import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestCasesSoftwareTesting {
    @Test
    public void signInPassedTestCase() {
        //test case for not to sign in with invalid password
        System.setProperty("webdriver.chrome.driver","E:\\chrome driver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("waqas@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("1234567");
        webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();
        String a="Authentication failed.";
        System.out.println(a);
       String r= webDriver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
        Assert.assertEquals(a,r);
        //Test case passed because we provided wrong email and password and errors are compared
    }

    @Test
    public void signInFailedTestCase() {
        //test case log in with password less than 8 characters
        System.setProperty("webdriver.chrome.driver","E:\\chrome driver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("waqas@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();
        String a="Authentication failed.";
        System.out.println(a);
        String r= webDriver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
        Assert.assertEquals(a,r);
        //Test case Failed because as per standart rules for Password must contain at least 8 charaters with letters and numbers
        //but this website logs in
    }


    @Test
    public void signUpFailedTestCase() {
        //test case to check if it accept less than 8 letters as a password
        System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys("w31323@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys("Waqas");
        webDriver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys("khan");
        webDriver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345");
        String expected=webDriver.findElement(By.xpath("//*[@id=\"create-account_form\"]/h3")).getText();
        String actual="INVALID PASSWORD";
        Assert.assertEquals(actual,expected);

    }
    @Test
    public void signUpPassedTestCase1(){
        //test case to check it doesnt accept email already registered
        System.setProperty("webdriver.chrome.driver","E:\\chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
        driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys("waqas@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();
        String expected=driver.findElement(By.xpath("//*[@id=\"create-account_form\"]/h3")).getText();
        String actual="CREATE AN ACCOUNT";
        Assert.assertEquals(actual,expected);



    }



    @Test
    public void signUpTestCase3() throws InterruptedException {
        //test case to sign up complelty

        System.setProperty("webdriver.chrome.driver","E:\\chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
        driver.findElement(By.cssSelector("[name='email_create']")).sendKeys("testeer@test.com");

        //Click on "Create an account"
        driver.findElement(By.xpath("//button[@name=\"SubmitCreate\"]")).click();

        //Select Title
        driver.findElement(By.xpath("//input[@id=\"id_gender1\"]")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys("Test User");
        driver.findElement(By.name("customer_lastname")).sendKeys("Vsoft");
        driver.findElement(By.id("passwd")).sendKeys("PKR@PKR");

        // Enter your address
        driver.findElement(By.id("firstname")).sendKeys("Test User");
        driver.findElement(By.id("lastname")).sendKeys("waqas");
        driver.findElement(By.id("company")).sendKeys("waqas");
        driver.findElement(By.id("address1")).sendKeys("Test 81/1,2nd cross");
        driver.findElement(By.id("city")).sendKeys("XYZ");

        // Select State
        WebElement statedropdown=driver.findElement(By.name("id_state"));
        Select oSelect=new Select(statedropdown);
        oSelect.selectByValue("4");

        driver.findElement(By.name("postcode")).sendKeys("51838");

        // Select Country
        WebElement countrydropDown=driver.findElement(By.name("id_country"));
        Select oSelectC=new Select(countrydropDown);
        oSelectC.selectByVisibleText("United States");

        //Enter Mobile Number
        driver.findElement(By.id("phone_mobile")).sendKeys("234567890");
        driver.findElement(By.xpath("//input[@name=\"alias\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"alias\"]")).sendKeys("Office");
        driver.findElement(By.name("submitAccount")).click();
        String userText=driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).getText();
// Validate that user has created
        if(userText.contains("waqas")) {
            System.out.println("User Verified,Test case Passed");
        }
        else {
            System.out.println("User Verification Failed,Test case Failed");
        }


    }

}
