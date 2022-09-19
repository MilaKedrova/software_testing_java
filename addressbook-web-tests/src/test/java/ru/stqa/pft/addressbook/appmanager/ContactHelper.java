package ru.stqa.pft.addressbook.appmanager;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper  extends HelperBase{

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

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

    public void modify(ContactsData contact) {
        selectContactById(contact.getId());
//        initContactModification(0);
        filContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactsData contact) {
        selectContactById(contact.getId());
        deleteSelected();
        contactCache = null;
//        acceptAlert();
    }

    public void addNewContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void modifyContact() {
        click(By.name("modifiy"));
    }

    public void deleteSelected() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactsData contact, boolean b) {
        addNewContactPage();
        filContactForm(contact, true);
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void checkExistence() {
        if (all().size() == 0) {
            addNewContactPage();
            create(new ContactsData().withFirstName("Clark").withLastName("Kent").withPhone("454545").
                    withEmail("superman@mail.ru").withAddress("smallville").withGroup("test10"), true);
        }
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

//    public Contacts all() {
//        if (contactCache != null) {
//            return new Contacts(contactCache);
//        }
//
//        contactCache = new Contacts();
//        List<WebElement> elements = wd.findElements(By.name("entry"));
//        for (WebElement element : elements) {
//            List<WebElement> cells = element.findElements(By.tagName("td"));
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            ContactsData contacts = new ContactsData().withId(id).
//                    withFirstName(cells.get(2).getText()).withLastName(cells.get(1).getText());
//            contactCache.add(contacts);
//        }
//        return new Contacts(contactCache);
//    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactsData().withId(id).withFirstName(firstname).withLastName(lastname).
                    withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

//    public Set<ContactsData> all() {
//        Set<ContactsData> contacts = new HashSet<ContactsData>();
//        List<WebElement> rows = wd.findElements(By.name("entry"));
//        for (WebElement row : rows) {
//            List<WebElement> cells = row.findElements(By.tagName("td"));
//            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
//            String lastname = cells.get(1).getText();
//            String firstname = cells.get(2).getText();
//            String[] phones = cells.get(5).getText().split("\n");
//            contacts.add(new ContactsData().withId(id).withFirstName(firstname).withLastName(lastname).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
//        }
//        return contacts;
//    }

    public ContactsData infoFromEditForm(ContactsData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactsData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}
