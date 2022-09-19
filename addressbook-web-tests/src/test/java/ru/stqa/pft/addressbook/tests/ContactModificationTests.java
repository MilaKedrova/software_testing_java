package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().checkExistence();
        app.goTo().contactPage();
        app.contact().checkExistence();
        app.goTo().contactPage();
    }

    @Test
    public void contactModificationTests() {
        Contacts before = app.contact().all();
        ContactsData modifiedContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(modifiedContact.getId()).
                withFirstName("Kal").withLastName("El").withPhone("123456").withEmail("superman@mail.ru").
                withAddress("krypton");

        app.contact().modify(contact);
        app.goTo().contactPage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
