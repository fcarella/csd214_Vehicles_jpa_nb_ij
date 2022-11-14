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
       EntityManagerFactory emf=null;
        EntityManager em=null;
        
        try{
            emf=Persistence.createEntityManagerFactory("default");
            em=emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created ("+emf+")");
            em.getTransaction().begin();
            Car car=new Car();
            car.setMake("Ford");
            car.setModel("Bronco");
            car.setYear(2022);
            car.setVin("111111");
            car.setNumber_of_doors(4);
            
            Truck truck=new Truck();
            truck.setMake("Ford");
            truck.setModel("Bronco");
            truck.setYear(2022);
            truck.setVin("111111");
            truck.setNumber_of_axles(3);
            
            Motorcycle motorcycle=new Motorcycle();
            motorcycle.setMake("Ducati");
            motorcycle.setModel("Superleggerra");
            motorcycle.setYear(2023);
            motorcycle.setVin("111111");
            motorcycle.setHas_a_sidecar(true);

//            Vehicle v=new Vehicle();
//            v.setMake("make");
//            v.setModel("model");
//            v.setYear(2022);
//            v.setVin("989998989");
//            em.persist(v);
            
            em.persist(car);
            em.persist(truck);
            em.persist(motorcycle);
            
//            em.persist(truck);
//            em.persist(motorcycle);
            em.getTransaction().commit();
            
            
            List<Vehicle> ListOfVehicles = em.createQuery("SELECT c FROM Vehicle c").getResultList();
            System.out.println("List of Vehicles");
            for(Vehicle vehicle:ListOfVehicles){
                System.out.println(vehicle);
            }
            
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(emf!=null)
                emf.close();
//            if(em!=null)
//                em.close();
        }
    }

   
    
}
