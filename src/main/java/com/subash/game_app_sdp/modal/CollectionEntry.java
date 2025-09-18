package com.subash.game_app_sdp.modal;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "collections")
public class CollectionEntry {
    @Id
    private String id;
    private Date date;
    private Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CollectionEntry() {
    }

    public CollectionEntry(String id, Date date, Double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }
}


