package org.csd214.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author fcarella
 */
@Entity
public class Truck extends Vehicle {

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

}
