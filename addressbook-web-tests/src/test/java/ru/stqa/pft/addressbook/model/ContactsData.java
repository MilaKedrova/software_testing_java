package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")
@XStreamAlias("contact")
public class ContactsData {
    @Id
    @Column(name="id")
    @XStreamOmitField
    private int id = 0;
    @Expose
    @Column(name="firstName")
    private String firstName;
    @Expose
    @Column(name="lastName")
    private String lastName;
    @Expose
    @Transient
    private String phone;
    @Expose
    @Column(name="email")
    @Type(type = "text")
    private String email;
    @Column(name="email2")
    @Type(type = "text")
    private String email2;
    @Column(name="email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String allEmails;
    @Expose
    @Column(name="address")
    @Type(type = "text")
    private String address;
    @Transient
    private String group;
    @Column(name="home")
    @Type(type = "text")
    private String homePhone;
    @Column(name="mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name="work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Column(name="photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactsData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactsData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

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

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactsData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactsData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactsData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactsData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ContactsData withAddress(String address) {
        this.address = address;
        return this;
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

    public ContactsData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "ContactsData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
