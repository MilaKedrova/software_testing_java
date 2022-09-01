package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactHelper  extends HelperBase{
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void filContactForm(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getFirstName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("home"), contactsData.getPhone());
        type(By.name("email"), contactsData.getEmail());
        type(By.name("address"), contactsData.getAddress());
//        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
//        click(By.xpath("//div[@id='content']/form/input[21]"));
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void initContactModification() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[3]/td[8]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void viewContactDetails() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[7]"));
    }

    public void modifyContact() {
        click(By.name("modifiy"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
}
