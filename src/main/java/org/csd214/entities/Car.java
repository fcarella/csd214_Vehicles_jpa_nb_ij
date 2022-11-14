package org.csd214.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author fcarella
 */

@Entity
public class Car extends Vehicle implements Serializable {

    @Basic
    private int number_of_doors;

    public int getNumber_of_doors() {
        return number_of_doors;
    }

    public void setNumber_of_doors(int number_of_doors) {
        this.number_of_doors = number_of_doors;
    }

    @Override
    public String toString() {
        return "Car{" + " make=" + getMake() + ", model=" + getModel() + ", year=" + getYear() + ", vin=" + getVin() + ", number_of_doors=" + number_of_doors + '}';
    }

    public Car() {
    }

    public Car(int number_of_doors, String make, String model, int year, String vin) {
        super(make, model, year, vin);
        this.number_of_doors = number_of_doors;
    }

}