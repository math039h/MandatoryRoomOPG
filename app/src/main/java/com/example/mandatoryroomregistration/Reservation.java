package com.example.mandatoryroomregistration;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Time;

class Reservation implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fromTime")
    @Expose
    private Time fromTime;
    @SerializedName("toTime")
    @Expose
    private Time toTime;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("roomId")
    @Expose
    private Integer roomId;

    public Reservation() {
    }

    public Reservation(Time fromTime, Time toTime, String userId, String purpose, Integer roomId) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.userId = userId;
        this.purpose = purpose;
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) { this.purpose = purpose; }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) { this.roomId = roomId; }

    @NonNull
    @Override
    public String toString() {
        return fromTime + ": " + toTime + " " + userId + ", " + purpose + ", " + roomId;
    }
}

