package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactModificationTests extends TestBase {

    @Test
        public void contactModificationTests () {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().checkGroupExistence();
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().checkContactExistence();
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().initContactModification();
            app.getContactHelper().filContactForm(new ContactsData("Kal", "El", "123456", "superman@mail.ru", "krypton", null), false);
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().gotoHomePage();
    }
}
