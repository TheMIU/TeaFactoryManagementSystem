package lk.ijse.tfms.entity;

import java.time.LocalDate;

public class TeaBuyers {
    private String buyer_ID;
    private LocalDate date;

    public TeaBuyers() {
    }

    public TeaBuyers(String buyer_ID, LocalDate date) {
        this.buyer_ID = buyer_ID;
        this.date = date;
    }

    public String getBuyer_ID() {
        return buyer_ID;
    }

    public void setBuyer_ID(String buyer_ID) {
        this.buyer_ID = buyer_ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TeaBuyers{" +
                "buyer_ID='" + buyer_ID + '\'' +
                ", date=" + date +
                '}';
    }
}
