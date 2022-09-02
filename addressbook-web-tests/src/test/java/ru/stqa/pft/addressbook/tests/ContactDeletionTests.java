package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactDeletionTests () {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().checkGroupExistence();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().checkContactExistence();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.acceptAlert();
    }
}
