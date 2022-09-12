package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test (enabled = false)
    public void contactDeletionTests () {
        app.goTo().groupPage();
        app.group().checkGroupExistence();
        app.goTo().gotoHomePage();
        app.getContactHelper().addNewContactPage();
        app.getContactHelper().checkContactExistence();
        app.goTo().gotoHomePage();
        List<ContactsData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.acceptAlert();
        app.goTo().gotoHomePage();
        List<ContactsData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
