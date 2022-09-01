package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;


public class ContactModificationTests extends TestBase {

    @Test
        public void contactModificationTests () {
            app.getContactHelper().initContactModification();
            app.getContactHelper().filContactForm(new ContactsData("Kal", "El", "123456", "superman@mail.ru", "krypton", null), false);
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().gotoHomePage();
    }
}
