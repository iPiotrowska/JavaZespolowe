package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    public int getId() {
        return id.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public int getPhone() {
        return phone.get();
    }

    public Person(int id,String firstName, String lastName, int phone)
    {
        this.id= new SimpleIntegerProperty(id);
        this.firstName= new SimpleStringProperty(firstName);
        this.lastName=new SimpleStringProperty(lastName);
        this.phone=new SimpleIntegerProperty(phone);
    }

    private SimpleIntegerProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleIntegerProperty phone;

    public SimpleIntegerProperty idProperty() {
        return id;
    }
}
