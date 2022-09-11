package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().checkGroupExistence();
        app.getNavigationHelper().gotoHomePage();
        List<ContactsData> before = app.getContactHelper().getContactList();
        ContactsData contact = new ContactsData("Clarky", "Kent", "454545", "superman@mail.ru", "smallville", "test1");
        app.getContactHelper().addNewContactPage();
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHomePage();
        List<ContactsData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        app.getNavigationHelper().gotoHomePage();

//        before.add(contact);
//        Comparator<? super ContactsData> byId = (Comparator<ContactsData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
