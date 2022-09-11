package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper  extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void filContactForm(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getFirstName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("home"), contactsData.getPhone());
        type(By.name("email"), contactsData.getEmail());
        type(By.name("address"), contactsData.getAddress());
        if (creation) {
            new Select(wd.findElement(By.xpath("//*[@name='new_group']"))).selectByVisibleText(contactsData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.xpath("//*[@name='new_group']")));
        }
        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void addNewContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]")).get(index).click();
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

    public void createContact(ContactsData contact, boolean b) {
        filContactForm(contact, true);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void checkContactExistence() {
        if (! isThereAContact()) {

            createContact(new ContactsData("Clark", "Kent", "454545", "superman@mail.ru", "smallville", "test1"), true);
        }
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactsData> getContactList() {
        List<ContactsData> contact = new ArrayList<ContactsData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactsData contacts = new ContactsData(id, cells.get(2).getText(), cells.get(1).getText(), null, null, null, null);
            contact.add(contacts);
        }
        return contact;
    }
}
