package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().addNewContactPage();
            app.contact().createWithMoreContacts(new ContactsData().withFirstName("Clark")
                    .withLastName("Kent").withEmail("superman@mail.ru").withAddress("smallville")
                    .withGroup("test 1").withHomePhone("123456").withMobilePhone("5545-45-45").
                    withWorkPhone("7(457)898-45-45").withEmail("superman@mail.ru").
                    withEmail2("hero-123@bk.ru").withEmail3("777@google.com"), true);
        }
        app.goTo().contactPage();
    }

    @Test
    public void contactModificationTests() {
        Contacts before = app.db().contacts();
        ContactsData modifiedContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(modifiedContact.getId())
                .withFirstName("Kal").withLastName("El").withEmail("superman@mail.ru").withAddress("krypton")
                .withGroup("test 1").withHomePhone("123456").withMobilePhone("5545-45-45").withWorkPhone("7(457)898-45-45")
                .withEmail2("hero-123@bk.ru").withEmail3("777@google.com");

        app.contact().modifyWithoutGroup(contact);
        app.goTo().contactPage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
