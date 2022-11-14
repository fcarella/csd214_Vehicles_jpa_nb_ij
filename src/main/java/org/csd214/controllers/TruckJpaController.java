/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.csd214.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.csd214.controllers.exceptions.NonexistentEntityException;
import org.csd214.entities.Truck;

/**
 *
 * @author fcarella
 */
public class TruckJpaController implements Serializable {

    public TruckJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Truck truck) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(truck);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Truck truck) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            truck = em.merge(truck);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = truck.getId();
                if (findTruck(id) == null) {
                    throw new NonexistentEntityException("The truck with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Truck truck;
            try {
                truck = em.getReference(Truck.class, id);
                truck.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The truck with id " + id + " no longer exists.", enfe);
            }
            em.remove(truck);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Truck> findTruckEntities() {
        return findTruckEntities(true, -1, -1);
    }

    public List<Truck> findTruckEntities(int maxResults, int firstResult) {
        return findTruckEntities(false, maxResults, firstResult);
    }

    private List<Truck> findTruckEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Truck as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Truck findTruck(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Truck.class, id);
        } finally {
            em.close();
        }
    }

    public int getTruckCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Truck as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
