package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() throws Exception {
        app.goTo().groupPage();
        app.group().checkGroupExistence();
        app.goTo().gotoHomePage();
        List<ContactsData> before = app.getContactHelper().getContactList();
        ContactsData contact = new ContactsData("Clarky", "Kent", "454545", "superman@mail.ru", "smallville", "test1");
        app.getContactHelper().addNewContactPage();
        app.getContactHelper().createContact(contact, true);
        app.goTo().gotoHomePage();
        List<ContactsData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        app.goTo().gotoHomePage();

        before.add(contact);
        Comparator<? super ContactsData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
