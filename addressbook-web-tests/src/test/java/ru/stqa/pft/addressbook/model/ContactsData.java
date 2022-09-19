package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactsData {

    private int id = 0;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

//    public ContactsData(int id, String firstName, String lastName, String phone, String email, String address, String group) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.email = email;
//        this.address = address;
//        this.group = group;
//    }
//
//    public ContactsData(String firstName, String lastName, String phone, String email, String address, String group) {
//        this.id = 0;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.email = email;
//        this.address = address;
//        this.group = group;
//    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactsData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactsData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactsData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "ContactsData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getGroup() {
        return group;
    }

    public ContactsData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactsData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactsData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactsData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactsData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactsData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactsData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsData that = (ContactsData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        return Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
