package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Book {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty date;
    private SimpleStringProperty person;


    public Book(int id, String title, String author, String date, String person){
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.date = new SimpleStringProperty(date);
        this.person = new SimpleStringProperty(person);
    }

    public int getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getPerson() {
        return person.get();
    }



}
