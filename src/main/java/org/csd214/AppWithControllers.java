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
import org.csd214.controllers.CarJpaController;
import org.csd214.controllers.MotorcycleJpaController;
import org.csd214.controllers.TruckJpaController;
import org.csd214.controllers.VehicleJpaController;
import org.csd214.entities.Car;
import org.csd214.entities.Motorcycle;
import org.csd214.entities.Truck;
import org.csd214.entities.Vehicle;

/**
 *
 * @author fcarella
 */
public class AppWithControllers {

    public AppWithControllers() {
    }

    void run() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("default");
//            em = emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created (" + emf + ")");

            VehicleJpaController vehicleController = new VehicleJpaController(emf);
            CarJpaController carController = new CarJpaController(emf);
            MotorcycleJpaController motorcycleController = new MotorcycleJpaController(emf);
            TruckJpaController truckController = new TruckJpaController(emf);

            Car car = new Car(4, "Ford", "Bronco", 2023, "12345");
            Truck truck = new Truck(3, "Ford", "F150", 2020, "23456");
            Motorcycle motorcycle = new Motorcycle(false, "Ducati", "Superleggerra", 2023, "987654");
            Vehicle vehicle = new Vehicle("make", "model", 0, "vin");
            
            carController.create(car);
            truckController.create(truck);
            motorcycleController.create(motorcycle);
            vehicleController.create(vehicle);
            
            List<Vehicle>       vehicles    = vehicleController.findVehicleEntities();
            List<Car>           cars        = carController.findCarEntities();
            List<Truck>         trucks      = truckController.findTruckEntities();
            List<Motorcycle>    motorcycles = motorcycleController.findMotorcycleEntities();
            
            System.out.println("------------Vehicles-----------");
            for(Vehicle v:vehicles)
                System.out.println(v);
            System.out.println("------------Cars-----------");
            for(Car c:cars)
                System.out.println(c);
            System.out.println("------------Trucks-----------");
            for(Truck t:trucks)
                System.out.println(t);
            System.out.println("------------Motorcyles-----------");
            for(Motorcycle m:motorcycles)
                System.out.println(m);
            
            // edit
            Car c2=carController.findCar(car.getId());
            c2.setMake("Chevy");
            c2.setModel("Camaro");
            carController.edit(c2);
            System.out.println("------------Cars-----------");
            cars        = carController.findCarEntities();
            for(Car c:cars)
                System.out.println(c);
            // delete

            Car car2 = new Car(4, "Toyaota", "Tundra", 2024, "12345");
            carController.create(car2);
            cars        = carController.findCarEntities();
            for(Car c:cars)
                System.out.println(c);

            Car car3=carController.findCar(car2.getId());
            car3.setMake("Toyota");
            carController.edit(car3);
            cars        = carController.findCarEntities();
            for(Car c:cars)
                System.out.println(c);

            carController.destroy(car3.getId());
            cars        = carController.findCarEntities();
            for(Car c:cars)
                System.out.println(c);




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
