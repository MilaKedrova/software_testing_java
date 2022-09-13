package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


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
        Contacts before = app.contact().all();
        ContactsData modifiedContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(modifiedContact.getId()).withFirstName("Kal").withLastName("El").
                withPhone("123456").withEmail("superman@mail.ru").withAddress("krypton").withGroup(null);
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
