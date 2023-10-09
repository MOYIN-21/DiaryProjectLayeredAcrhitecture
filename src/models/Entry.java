package models;


import java.time.LocalDateTime;

public class Entry {
    private String owner;
    private String title;
    private String body;
    private LocalDateTime dateCreated;
    private int id;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = Integer.parseInt(String.valueOf(id));
    }

    public int getEntryId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "userName='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dateCreated=" + dateCreated +
                ", id=" + id +
                '}';
    }
}
