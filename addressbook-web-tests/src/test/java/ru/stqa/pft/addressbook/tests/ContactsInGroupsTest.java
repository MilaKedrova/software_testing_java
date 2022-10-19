package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactsInGroupsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().addNewContactPage();
            app.contact().createWithMoreContacts(new ContactsData().withFirstName("Clark")
                    .withLastName("Kent").withEmail("superman@mail.ru").withAddress("smallville")
                    .withHomePhone("123456").withMobilePhone("5545-45-45")
                    .withWorkPhone("7(457)898-45-45").withEmail("superman@mail.ru")
                    .withEmail2("hero-123@bk.ru").withEmail3("777@google.com"), true);
            app.goTo().contactPage();
        }

        if (app.db().groups().size() == 0)
        {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(String.format("test1")));
            app.goTo().contactPage();
        }
    }

    @Test
    public void addContactToGroup() {
        ContactsData selectContact = app.db().contacts().stream().iterator().next();
        GroupData selectGroup = app.db().groups().stream().iterator().next();

        if (!selectContact.getGroups().isEmpty() && selectContact.getGroups().contains(selectGroup)) {
            app.contact().selectGroupForDelete(selectGroup);
            app.contact().selectContactById(selectContact.getId());
            app.contact().deleteContactFromGroup();
            assertThat(selectContact.getGroups().without(selectGroup), equalTo(app.db().contacts().stream()
                    .filter((c) -> c.getId() == selectContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
            app.goTo().contactPage();
        }
        app.goTo().contactPage();
        app.contact().selectAll();
        app.contact().selectContactById(selectContact.getId());
        app.contact().selectGroupByName(selectGroup);
        app.contact().submitGroup();
        assertThat(selectContact.getGroups().withAdded(selectGroup), equalTo(app.db().contacts().stream().
                filter((c) -> c.getId() == selectContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
    }

    @Test
    public void deleteContactFromGroup () {
        ContactsData selectContact = app.db().contacts().stream().iterator().next();
        GroupData selectGroup = app.db().groups().stream().iterator().next();

        if (selectContact.getGroups().isEmpty() || !selectContact.getGroups().contains(selectGroup))
        {
            app.contact().selectAll();
            app.contact().selectContactById(selectContact.getId());
            app.contact().selectGroupByName(selectGroup);
            app.contact().submitGroup();
            assertThat(selectContact.getGroups().withAdded(selectGroup), equalTo(app.db().contacts().stream().
                    filter((c) -> c.getId() == selectContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
            app.goTo().contactPage();
        }
        app.goTo().contactPage();
        app.contact().selectGroupForDelete(selectGroup);
        app.contact().selectContactById(selectContact.getId());
        app.contact().deleteContactFromGroup();

        assertThat(selectContact.getGroups().without(selectGroup), equalTo(app.db().contacts().stream().
                filter((c) -> c.getId() == selectContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
    }
}