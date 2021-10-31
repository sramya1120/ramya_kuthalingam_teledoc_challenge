import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;

public class DemoTable {
  
    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ramya\\IdeaProjects\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.way2automation.com/angularjs-protractor/webtables/");
        System.out.println(driver.getTitle());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        DemoTable demoTable = new DemoTable();
        demoTable.addRecord(driver);
        demoTable.validateAddRecord(driver, "FName test");
        demoTable.deleteRecord(driver);
        demoTable.validateDeleteRecord(driver, "mark");

    }


//    Adding new record to the table
    public void addRecord(WebDriver driver) {

        driver.findElement(By.xpath("//thead[1]/tr[2]/td[1]/button[1]")).click();
        driver.findElement(By.name("FirstName")).sendKeys("FName test");
        driver.findElement(By.name("LastName")).sendKeys("LName test");
        driver.findElement(By.name("UserName")).sendKeys("UserName test");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@value='15']")).click();

        Select select = new Select(driver.findElement(By.name("RoleId")));
        select.selectByVisibleText("Admin");

        driver.findElement(By.name("Email")).sendKeys("testuser@test.com");
        driver.findElement(By.name("Mobilephone")).sendKeys("123456789");

        driver.findElement(By.xpath(" //button[contains(text(),'Save')]")).click();

    }

//    Validating the added record
    public void validateAddRecord(WebDriver driver, String firstname) {
        WebElement table = driver.findElement(By.xpath("//body/table[1]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<WebElement> columnsList = null;
        boolean isUserAdded = false;
        for (WebElement row : rows) {
            columnsList = row.findElements(By.tagName("td"));
            if (!columnsList.isEmpty() && columnsList.get(0).getText().equalsIgnoreCase(firstname)) {
                isUserAdded = true;
                System.out.println(row.getText());
                break;
            }
        }
        if (isUserAdded) System.out.println(firstname + " " + " user added");

    }

//    Deleting a user from the table
    public void deleteRecord(WebDriver driver) {
        driver.findElement(By.xpath("//tbody/tr[4]/td[11]/button[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
    }

//    Validating the deleted record
    public void validateDeleteRecord(WebDriver driver, String firstname) {
        WebElement table = driver.findElement(By.xpath("//body/table[1]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<WebElement> columnsList = null;
        boolean isUserDeleted = true;
        for (WebElement row : rows) {
            columnsList = row.findElements(By.tagName("td"));
            if (!columnsList.isEmpty() && columnsList.get(0).getText().equalsIgnoreCase(firstname)) {
                isUserDeleted = false;
                break;
            }
        }
        if (isUserDeleted) System.out.println(firstname + " " + " user deleted");
    }
}



