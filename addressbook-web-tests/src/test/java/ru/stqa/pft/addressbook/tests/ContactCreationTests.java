package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().checkExistence();
        app.goTo().contactPage();
    }

    @Test (enabled = false)
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactsData contact = new ContactsData().
                withFirstName("Clarky").withLastName("Kent").withPhone("454545").
                withEmail("superman@mail.ru").withAddress("smallville").withGroup("test10");
        app.contact().create(contact, true);
        app.goTo().contactPage();
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        app.goTo().contactPage();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactsData contact = new ContactsData().
                withFirstName("Clarky").withLastName("Kent").withPhone("454545").
                withEmail("superman@mail.ru").withAddress("smallville").withGroup("test10");
        app.contact().create(contact, true);
        app.goTo().contactPage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        app.goTo().contactPage();

        assertThat(after, equalTo(before));
    }
}
