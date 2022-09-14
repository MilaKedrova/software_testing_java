package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.*;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().checkExistence();
        app.goTo().homePage();
    }

    @Test
    public void contactModificationTests() {
        app.contact().checkExistence();
        Set<ContactsData> before = app.contact().all();
        ContactsData modifiedContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(modifiedContact.getId()).withFirstName("Kal").withLastName("El").
                withPhone("123456").withEmail("superman@mail.ru").withAddress("krypton").withGroup(null);
        app.contact().modify(contact);
        app.goTo().homePage();
        Set<ContactsData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
