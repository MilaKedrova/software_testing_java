package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().contactPage();
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactsData.class);
            xstream.allowTypes(new Class[]{ContactsData.class});
            List<ContactsData> contacts = (List<ContactsData>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactsData> contacts = gson.fromJson(json, new TypeToken<List<ContactsData>>() {
            }.getType()); //List<GroupData>.class
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreationFromFile(ContactsData contact) throws Exception {
        Contacts before = app.db().contacts();
        app.contact().createWithMoreContacts(contact, true);
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        app.goTo().contactPage();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.db().contacts();
        ContactsData contact = new ContactsData().
                withFirstName("Clarky").withLastName("Kent").withMobilePhone("454545").withHomePhone("111")
                .withWorkPhone("454545").withEmail("superman@mail.ru").withEmail2("superman2@mail.ru").
                withEmail3("superman3@mail.ru").withAddress("smallville");
        app.contact().createWithoutGroup(contact, true);
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        app.goTo().contactPage();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
    @Test
    public void testContactCreationWithGroup() throws Exception {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/test.jpeg");
        ContactsData newContact = new ContactsData().
                withFirstName("Clarky").withLastName("Kent").withMobilePhone("454545").withHomePhone("111")
                .withWorkPhone("454545").withEmail("superman@mail.ru").withEmail2("superman2@mail.ru").
                withEmail3("superman3@mail.ru").withAddress("smallville").withPhoto(photo).inGroup(groups.iterator().next());
        app.contact().createWithMoreContacts(newContact, true);
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        app.goTo().contactPage();

        assertThat(after, equalTo(after.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() throws Exception {
        Contacts before = app.db().contacts();
        ContactsData contact = new ContactsData().
                withFirstName("Clarky'").withLastName("Kent").withPhone("454545").
                withEmail("superman@mail.ru").withAddress("smallville");
        app.contact().createWithoutGroup(contact, true);
        app.goTo().contactPage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        app.goTo().contactPage();
        assertThat(after, equalTo(before));
    }


}
