package org.csd214.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author fcarella
 */
@Entity
public class Truck extends Vehicle implements Serializable {

    @Basic
    private int number_of_axles;

    public int getNumber_of_axles() {
        return number_of_axles;
    }

    public void setNumber_of_axles(int number_of_axles) {
        this.number_of_axles = number_of_axles;
    }

    @Override
    public String toString() {
        return "Truck{" + " make=" + getMake() + ", model=" + getModel() + ", year=" + getYear() + ", vin=" + getVin() + ", number_of_axles=" + number_of_axles + '}';
    }

    public Truck(int number_of_axles, String make, String model, int year, String vin) {
        super(make, model, year, vin);
        this.number_of_axles = number_of_axles;
    }

    public Truck() {
    }

}
