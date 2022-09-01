package ru.stqa.pft.addressbook.model;

public class ContactsData {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final String address;
    private String group;

    public ContactsData(String firstName, String lastName, String phone, String email, String address, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }
}
