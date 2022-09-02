package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ViewDetailsAndModifyContactTests extends TestBase {

    @Test
    public void viewDetailsAndModifyContactTests() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactsData("Clark", "Kent", "454545", "superman@mail.ru", "smallville", "test1"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().modifyContact();
        app.getContactHelper().filContactForm(new ContactsData("Kal", "El", "123456", "superman@mail.ru", "krypton", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
