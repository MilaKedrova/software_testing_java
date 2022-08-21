package ru.stqa.pft.addressbook;
import java.time.Duration;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  }

  @Test
  public void testContactCreation() throws Exception {
    login("admin", "secret");
    addNewContactPage();
    finContactForm(new NameContactData("Clark", "Kent"), new ContactsContactData("1234567", "superman@mail.ru", "Smallville"));
    gotoHomePage();
    logout();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private void finContactForm(NameContactData nameContactData, ContactsContactData contactsContactData) {
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(nameContactData.getFirstName());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(nameContactData.getLastName());
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(contactsContactData.getPhone());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactsContactData.getEmail());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactsContactData.getAddress());
    wd.findElement(By.xpath("//*/text()[normalize-space(.)='']/parent::*")).click();
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void addNewContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
