package com.example.mandatoryroomregistration;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class Rooms implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    public Rooms() {
    }

    public Rooms(Integer id, String name, String description, Integer capacity, String remarks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.remarks = remarks;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + '\'' +
                ", Name: " + name + '\'' +
                ", Description: " + description + '\'' +
                ", Capacity: " + capacity + '\'' +
                ", Remarks: " + remarks + '\'';

    }
}

