package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactsData;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactContactsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().addNewContactPage();
            app.contact().createWithMoreContacts(new ContactsData().withFirstName("Clark").withLastName("Kent").withPhone("454545").
                    withEmail("superman@mail.ru").withAddress("smallville").withGroup("test10").withHomePhone("123456")
                    .withMobilePhone("5545-45-45").withWorkPhone("7(457)898-45-45").withEmail("superman@mail.ru").
                    withEmail2("hero-123@bk.ru").withEmail3("777@google.com"), true);
        }
    }

    @Test
    public void testContactContacts() {
        app.goTo().contactPage();
        ContactsData contact = app.contact().all().iterator().next();
        ContactsData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddresses(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactsData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactContactsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactsData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergeAddresses(ContactsData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
