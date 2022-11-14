package org.csd214.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author fcarella
 */

@Entity
public class Motorcycle extends Vehicle {

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

}