package ru.stqa.pft.addressbook;

public class ContactsContactData {
    private final String phone;
    private final String email;
    private final String address;

    public ContactsContactData(String phone, String email, String address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
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
}
