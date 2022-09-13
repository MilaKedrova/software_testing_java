package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void create(ContactsData contact, boolean b) {
        filContactForm(contact, true);
    }

    public void modify(ContactsData contact) {
        initContactModification(0);
        filContactForm(contact, false);
        submitContactModification();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
    }

    public void delete(ContactsData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void checkExistence() {
        if (list().size() == 0) {
            create(new ContactsData().withFirstName("Clarky").withLastName("Kent").withPhone("454545").
                    withEmail("superman@mail.ru").withAddress("smallville").withGroup("test10"), true);
        }
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactsData> list() {
        List<ContactsData> contact = new ArrayList<ContactsData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contact.add(new ContactsData().withId(id).withFirstName(cells.get(2).getText()).
                    withLastName(cells.get(1).getText()).withPhone(null).withEmail(null).withAddress(null).withGroup(null));
        }
        return contact;
    }

    public Set<ContactsData> all() {
        Set<ContactsData> contact = new HashSet<ContactsData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contact.add(new ContactsData().withId(id).withFirstName(cells.get(2).getText()).
                    withLastName(cells.get(1).getText()).withPhone(null).withEmail(null).withAddress(null).withGroup(null));
        }
        return contact;
    }
}
