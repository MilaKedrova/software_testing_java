package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    NavigationHelper navigationHelper = new NavigationHelper(wd);

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
            if (contactsData.getGroups().size() > 0) {
                Assert.assertTrue(contactsData.getGroups().size() == 1);
                new Select(wd.findElement(By.xpath("//*[@name='new_group']")))
                        .selectByVisibleText(contactsData.getGroups().iterator().next().getName());
            } else {
                Assert.assertFalse(isElementPresent(By.xpath("//*[@name='new_group']")));
            }
        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }
    }

    private void filContactFormWithoutGroup(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getFirstName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("home"), contactsData.getHomePhone());
        type(By.name("email"), contactsData.getEmail());
        type(By.name("email2"), contactsData.getEmail2());
        type(By.name("email3"), contactsData.getEmail3());
        type(By.name("address"), contactsData.getAddress());
        type(By.name("mobile"), contactsData.getMobilePhone());
        type(By.name("work"), contactsData.getWorkPhone());
        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void filContactFormWithMoreContacts(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getFirstName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("email"), contactsData.getEmail());
        type(By.name("email2"), contactsData.getEmail2());
        type(By.name("email3"), contactsData.getEmail3());
        type(By.name("address"), contactsData.getAddress());
        type(By.name("home"), contactsData.getHomePhone());
        type(By.name("mobile"), contactsData.getMobilePhone());
        type(By.name("work"), contactsData.getWorkPhone());
//        if (creation) {
//            new Select(wd.findElement(By.xpath("//*[@name='new_group']"))).selectByVisibleText(contactsData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.xpath("//*[@name='new_group']")));
//        }
        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

//    public void filContactFormWithEmails(ContactsData contactsData, boolean creation) {
//        type(By.name("firstname"), contactsData.getFirstName());
//        type(By.name("lastname"), contactsData.getLastName());
//        type(By.name("home"), contactsData.getPhone());
//        type(By.name("address"), contactsData.getAddress());
//        type(By.name("email"), contactsData.getEmail());
//        type(By.name("email2"), contactsData.getEmail2());
//        type(By.name("email3"), contactsData.getEmail3());
//        if (creation) {
//            new Select(wd.findElement(By.xpath("//*[@name='new_group']"))).selectByVisibleText(contactsData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.xpath("//*[@name='new_group']")));
//        }
//        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
//        click(By.xpath("//div[@id='content']/form/input[21]"));
//    }

//    public void filContactFormWithAddresses(ContactsData contactsData, boolean creation) {
//        type(By.name("firstname"), contactsData.getFirstName());
//        type(By.name("lastname"), contactsData.getLastName());
//        type(By.name("home"), contactsData.getPhone());
//        type(By.name("email"), contactsData.getEmail());
//        type(By.name("address"), contactsData.getAddress());
//        if (creation) {
//            new Select(wd.findElement(By.xpath("//*[@name='new_group']"))).selectByVisibleText(contactsData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.xpath("//*[@name='new_group']")));
//        }
//        click(By.xpath("//*/text()[normalize-space(.)='']/parent::*"));
//        click(By.xpath("//div[@id='content']/form/input[21]"));
//    }

    public void modify(ContactsData contact) {
        selectContactById(contact.getId());
//        initContactModification(0);
        filContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void modifyWithoutGroup(ContactsData contact) {
        selectContactById(contact.getId());
//        initContactModification(0);
        filContactFormWithoutGroup(contact, false);
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

//    public void selectContactById(int id) {
//        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
//    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitGroup() {
        click(By.xpath("//div[@id='content']/form[2]/div[4]/input"));
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

    public void createWithoutGroup(ContactsData contact, boolean b) {
        addNewContactPage();
        filContactFormWithoutGroup(contact, true);
        contactCache = null;
    }

    public void createWithMoreContacts(ContactsData contact, boolean b) {
        addNewContactPage();
        filContactFormWithMoreContacts(contact, true);
        contactCache = null;
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

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
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCache.add(new ContactsData().withId(id).withFirstName(firstname).withLastName(lastname).
                    withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactsData infoFromEditForm(ContactsData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactsData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).
                withEmail3(email3).withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void selectGroupForContact() {
        String groupName = wd.findElement(By.xpath("//select[@name=\"to_group\"]")).getText();
        System.out.println(groupName);
    }

    public void selectAll()
    {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    }

    public void selectGroupByName(GroupData groupName) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(groupName.getId()));
        System.out.println(groupName);
    }

    public void addContactToGroup() {
        click(By.name("add"));
    }

    public void deleteContactFromGroup() {
        click(By.name("remove"));
    }

    public void selectGroupForDelete(GroupData selectGroup) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(selectGroup.getId()));
    }
}
