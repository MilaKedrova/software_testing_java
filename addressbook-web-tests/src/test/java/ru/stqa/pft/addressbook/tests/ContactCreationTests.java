package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().checkGroupExistence();
        app.getContactHelper().createContact(new ContactsData("Clark", "Kent", "454545", "superman@mail.ru", "smallville", "test1"), true);
        app.getNavigationHelper().gotoHomePage();
    }
}
