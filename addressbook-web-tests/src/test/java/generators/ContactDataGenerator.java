package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactsData;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;
//-f src/test/resources/contacts.json -c 3 -d json

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactsData> contacts = generateContact(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsCsv(List<ContactsData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactsData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
                                contact.getEmail(), contact.getAddress(), contact.getGroup(), contact.getHomePhone(),
                        contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail2(), contact.getEmail3()));

            }
        }
    }

    private void saveAsJson(List<ContactsData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactsData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactsData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

//    private void save(List <ContactsData> contacts, File file) throws IOException {
//        System.out.println(new File(".").getAbsolutePath());
//        Writer writer = new FileWriter(file);
//        for (ContactsData contact : contacts) {
//            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getAddress(), contact.getEmail()));
//        }
//    }

    private List<ContactsData> generateContact(int count) {
        List<ContactsData> contacts = new ArrayList<ContactsData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactsData()
                    .withFirstName(String.format("Тест %s", i))
                    .withLastName(String.format("Тест %s", i))
                    .withEmail(String.format("test%s@mail.ru", i))
                    .withAddress(String.format("г. Москва, %s", i))
                    .withHomePhone(String.format("12345%s", i))
                    .withMobilePhone(String.format("5545-45-4%s", i))
                    .withWorkPhone(String.format("7(457)898-44-5%s", i))
                    .withEmail2(String.format("hero-%s@bk.ru", i))
                    .withEmail3(String.format("777-%s@google.com", i)));
        }
        return contacts;
    }

}
