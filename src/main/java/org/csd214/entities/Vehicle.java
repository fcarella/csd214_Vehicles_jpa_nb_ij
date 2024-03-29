package org.csd214.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author fcarella
 */

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String make;
    @Basic
    private String model;
    @Basic
    private int year;
    @Basic
    private String vin;

    public Vehicle() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public Vehicle(String make, String model, int year, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Vehicle{" + " make=" + make + ", model=" + model + ", year=" + year + ", vin=" + vin + '}';
    }

}