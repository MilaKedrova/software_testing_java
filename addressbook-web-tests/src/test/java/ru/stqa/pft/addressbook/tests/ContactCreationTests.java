package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;




public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().addNewContactPage();
    app.getContactHelper().filContactForm(new ContactsData("Clark", "Kent", "454545", "superman@mail.ru", "smallville"));
    app.getNavigationHelper().gotoHomePage();
  }
}
