/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.mapjournal.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 *
 * @author john
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "pin_id")
public class Pin implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int pin_id;

    @Column(nullable = false)
    @Size(max = 45, message = "Pin name is max 45 chars.")
    private String name;

    @Column(nullable = false)
    private String moment;

    @Column
    @Size(max = 90, message = "Description is max 90 chars.")
    private String description;

    @Column
    private String comments;

    @ManyToMany
    @JoinTable(name = "pin_tag",
            joinColumns = {
                @JoinColumn(name = "ptPinId")},
            inverseJoinColumns = {
                @JoinColumn(name = "ptTagId")})
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pin_trip",
            joinColumns = {
                @JoinColumn(name = "ptrPinId")},
            inverseJoinColumns = {
                @JoinColumn(name = "ptrTripId")})
    private List<Trip> trips = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pinLocation", nullable = false)
    private Location location;

    public int getPin_id() {
        return pin_id;
    }

    public void setPin_id(int pin_id) {
        this.pin_id = pin_id;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
