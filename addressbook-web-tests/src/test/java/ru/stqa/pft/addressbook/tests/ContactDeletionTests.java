package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactDeletionTests () {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.acceptAlert();
    }
}
