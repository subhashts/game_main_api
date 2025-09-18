package com.subash.game_app_sdp.modal;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    private String memberId; // ref -> members._id
    private String gameId;   // ref -> games._id
    private Double amount;
    private Date dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Transaction() { }

    public Transaction(String id, String memberId, String gameId, Double amount, Date dateTime) {
        this.id = id;
        this.memberId = memberId;
        this.gameId = gameId;
        this.amount = amount;
        this.dateTime = dateTime;
    }
}


