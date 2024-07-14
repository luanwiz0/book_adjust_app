package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Book implements Serializable {
    long _id;
    String title;
    String author;
    String publisher;
    String price;
    String category;
    String description;

    public Book(long _id, String title, String author, String price, String category) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
    }
    public Book(String title, String author, String publisher, String price, String category, String description) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Book(long _id, String title, String author, String publisher, String price, String category, String description) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    // getter & setter
    public long get_id() {
        return _id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".");
        sb.append(title);
        sb.append("(");
        sb.append(author);
        sb.append(", ");
        sb.append(publisher);
        sb.append("): ");
        sb.append(price);
        sb.append(", ");
        sb.append(category);
        sb.append(", ");
        sb.append(description);
        return sb.toString();
    }
}
