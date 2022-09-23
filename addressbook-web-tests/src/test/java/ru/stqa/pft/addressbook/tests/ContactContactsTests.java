package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactsData;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactContactsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().checkExistence();
        app.goTo().contactPage();
        app.contact().checkExistenceWithContacts();
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
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactContactsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactsData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergeAddresses(ContactsData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
