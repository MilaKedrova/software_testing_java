package ru.stqa.pft.addressbook;

public class NameContactData {
    private final String firstName;
    private final String lastName;

    public NameContactData(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
