/* Scenario 1- Open http://demosite.center/wordpress/wp-login.php and type username and password and login
this test case should run 3 times with different set of data(data we have provided in the 2D array)
*/


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertTrue;

public class TestDDTFromArray {
    private WebDriver driver;
    private By userName =  By.id("user_login");
    private By passWord = By.name("pwd");
    private By loginBtn = By.xpath("//*[@id='wp-submit']");

    @BeforeMethod
    public void initialSetUp(){
        System.setProperty("webdriver.chrome.driver","D:\\Automation Testing\\DDT\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demosite.center/wordpress/wp-login.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(dataProvider = "wordpressdata")
    public void loginValidation(String username, String password) throws InterruptedException {
        driver.findElement(userName).sendKeys(username);
        driver.findElement(passWord).sendKeys(password);
        driver.findElement(loginBtn).click();

        Thread.sleep(5000);
        assertTrue(driver.getTitle().contains("Dashboard"),"User not able to login successfully");
        System.out.println("User successfully logged in");

    }


    @DataProvider(name = "wordpressdata")
    public Object[][] dataAccess(){
        Object[][] objdata = new Object[3][2];

        //1st data
        objdata[0][0] = "admin1";
        objdata[0][1] = "demo123";

        //2nd data
        objdata[1][0] = "admin";
        objdata[1][1] = "demo123";

        objdata[2][0] = "admin2";
        objdata[2][1] = "demo1234";
        return objdata;
    }

}
