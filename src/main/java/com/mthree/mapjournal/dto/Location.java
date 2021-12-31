/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.mapjournal.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 *
 * @author john
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "location_id")
public class Location implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int location_id;

    @Column
    private String address;

    @Column(nullable = false)
    @DecimalMin(value = "-90", inclusive = true)
    @DecimalMax(value = "90", inclusive = true)
    //@Digits(integer = 2, fraction = 14, message = "Latitude must be in the format of 12.85896458307113 or -12.85896458307113")
    private BigDecimal latitude;

    @Column(nullable = false)
    @DecimalMin(value = "-180", inclusive = true)
    @DecimalMax(value = "180", inclusive = true)
    //@Digits(integer = 3, fraction = 14, message = "Longitude must be in the format of 123.95945564315455 or -123.95945564315455")
    private BigDecimal longitude;

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
