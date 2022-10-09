package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactsInGroupsTest extends TestBase {

    int contactId;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(String.format("test1")));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().addNewContactPage();
            app.contact().createWithMoreContacts(new ContactsData().withFirstName("Clark")
                    .withLastName("Kent").withEmail("superman@mail.ru").withAddress("smallville")
                    .withHomePhone("123456").withMobilePhone("5545-45-45")
                    .withWorkPhone("7(457)898-45-45").withEmail("superman@mail.ru")
                    .withEmail2("hero-123@bk.ru").withEmail3("777@google.com"), true);
        }
        if (app.db().contacts().size() > 0) {
            List<ContactsData> contacts = new ArrayList<ContactsData>(app.db().contacts());
            for (ContactsData contact : contacts) {
                if (contact.getGroups().size() == 0) {
                    contactId = contact.getId();
                    System.out.println("contactId = " + contactId);
                    break;
                }
                if (contactId == 0) {
                    app.contact().addNewContactPage();
                    app.contact().createWithMoreContacts(new ContactsData().withFirstName("Clark")
                            .withLastName("Kent").withEmail("superman@mail.ru").withAddress("smallville")
                            .withHomePhone("123456").withMobilePhone("5545-45-45").
                            withWorkPhone("7(457)898-45-45").withEmail("superman@mail.ru").
                            withEmail2("hero-123@bk.ru").withEmail3("777@google.com"), true);
                    contactId = contact.getId();
                    System.out.println("555555contactId = " + contactId);
                }
            }
        }
    }

    @Test
    public void addContactToGroup() {
        Groups contactGroupsBefore = app.db().getContactById(contactId).getGroups();
        System.out.println("Группы, в которые входил контакт с id = " + contactId + ": " + contactGroupsBefore);
        app.goTo().contactPage();
        app.contact().selectContactById(contactId);
        app.contact().selectGroupForContact();
        app.contact().addContactToGroup();
        app.goTo().contactPage();
        Groups contactGroupsAfter = app.db().getContactById(contactId).getGroups();
        System.out.println("Группы, в которые был добавлен контакт с id = " + contactId + ": " + contactGroupsAfter);

    }

    @Test
    public void deleteContactFromGroup() {
        addContactToGroup();
        Groups contactGroupsBefore = app.db().getContactById(contactId).getGroups();
        System.out.println("777777 contactId = " + contactId);
        System.out.println("Группы, в которые входил контакт с id = " + contactId + ": " + contactGroupsBefore);
        app.goTo().contactPage();
        app.contact().selectGroupByName("test1");
        app.contact().selectContactById(contactId);
        app.contact().deleteContactFromGroup();
        Groups contactGroupsAfter = app.db().getContactById(contactId).getGroups();
        System.out.println("Группы, в которые был добавлен контакт с id = " + contactId + ": " + contactGroupsAfter);
    }
}