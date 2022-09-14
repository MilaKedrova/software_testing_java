package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.Set;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().checkExistence();
        app.goTo().homePage();
    }

    @Test
    public void testContactCreation() throws Exception {
        Set<ContactsData> before = app.contact().all();
        ContactsData contact = new ContactsData().withFirstName("Clarky").withLastName("Kent").withPhone("454545").
                withEmail("superman@mail.ru").withAddress("smallville").withGroup("test2");
        app.contact().addNewContactPage();
        app.contact().create(contact, true);
        app.goTo().homePage();
        Set<ContactsData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        app.goTo().homePage();

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
