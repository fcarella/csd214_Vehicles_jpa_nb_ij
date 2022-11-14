package org.csd214.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author fcarella
 */

@Entity
public class Motorcycle extends Vehicle implements Serializable {

    @Basic
    private boolean has_a_sidecar;

    public boolean isHas_a_sidecar() {
        return has_a_sidecar;
    }

    public void setHas_a_sidecar(boolean has_a_sidecar) {
        this.has_a_sidecar = has_a_sidecar;
    }

    @Override
    public String toString() {
        return "Motorcycle{" + " make=" + getMake() + ", model=" + getModel() + ", year=" + getYear() + ", vin=" + getVin() + ", has_a_sidecar=" + has_a_sidecar + '}';
    }

    public Motorcycle() {
    }

    public Motorcycle(boolean has_a_sidecar, String make, String model, int year, String vin) {
        super(make, model, year, vin);
        this.has_a_sidecar = has_a_sidecar;
    }

}