package lk.ijse.tfms.entity;

public class notes {
    private String id;
    private String note;

    public notes() {
    }

    public notes(String id, String note) {
        this.id = id;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "notes{" +
                "id='" + id + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
