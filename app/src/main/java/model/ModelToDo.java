package model;

public class ModelToDo {
    String title,description,date,ID;

    public ModelToDo() {
    }

    public ModelToDo(String title, String description, String date, String ID) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
