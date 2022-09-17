package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.HashSet;
import java.util.List;


public class ContactModificationTests extends TestBase {

    @Test
        public void contactModificationTests () {
            app.goTo().groupPage();
            app.group().checkExistence();
            app.goTo().gotoHomePage();
            app.getContactHelper().checkContactExistence();
            List<ContactsData> before = app.getContactHelper().getContactList();
            app.getContactHelper().initContactModification(0);
            ContactsData contact = new ContactsData(before.get(0).getId(), "Kal", "El", "123456", "superman@mail.ru", "krypton", null);
            app.getContactHelper().filContactForm(contact, false);
            app.getContactHelper().submitContactModification();
            app.goTo().gotoHomePage();
            List<ContactsData> after = app.getContactHelper().getContactList();
            Assert.assertEquals(after.size(), before.size());

            before.remove(0);
            before.add(contact);
            Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
