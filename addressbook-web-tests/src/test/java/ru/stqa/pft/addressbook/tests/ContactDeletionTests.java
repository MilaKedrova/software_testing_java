package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().checkExistence();
        app.goTo().homePage();
        app.contact().addNewContactPage();
        app.contact().checkExistence();
        app.goTo().homePage();
    }

    @Test
    public void contactDeletionTests () {
        Set<ContactsData> before = app.contact().all();
        ContactsData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.acceptAlert();
        app.goTo().homePage();
        Set<ContactsData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
