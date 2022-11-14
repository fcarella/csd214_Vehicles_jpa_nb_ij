/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.csd214;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.csd214.entities.Car;
import org.csd214.entities.Motorcycle;
import org.csd214.entities.Truck;
import org.csd214.entities.Vehicle;

/**
 *
 * @author fcarella
 */
public class App {

    void run() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created (" + emf + ")");
            em.getTransaction().begin();
            Car car = new Car(4, "Ford", "Bronco", 2023, "12345");

            Truck truck = new Truck(3, "Ford", "F150", 2020, "23456");

            Motorcycle motorcycle = new Motorcycle(false, "Ducati", "Superleggerra", 2023, "987654");
            

            Vehicle v=new Vehicle("make", "model", 0, "vin");
            em.persist(car);
            em.persist(truck);
            em.persist(motorcycle);
            em.persist(v);

            em.getTransaction().commit();

            List<Vehicle> ListOfVehicles = em.createQuery("SELECT c FROM Vehicle c").getResultList();
            System.out.println("List of Vehicles");
            for (Vehicle vehicle : ListOfVehicles) {
                System.out.println(vehicle);
            }

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (emf != null) {
                emf.close();
            }
//            if(em!=null)
//                em.close();
        }
    }

}
