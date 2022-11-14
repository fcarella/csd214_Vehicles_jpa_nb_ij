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
import org.csd214.entities.Motorcycle;

/**
 *
 * @author fcarella
 */
public class MotorcycleJpaController implements Serializable {

    public MotorcycleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Motorcycle motorcycle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(motorcycle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Motorcycle motorcycle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            motorcycle = em.merge(motorcycle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = motorcycle.getId();
                if (findMotorcycle(id) == null) {
                    throw new NonexistentEntityException("The motorcycle with id " + id + " no longer exists.");
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
            Motorcycle motorcycle;
            try {
                motorcycle = em.getReference(Motorcycle.class, id);
                motorcycle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motorcycle with id " + id + " no longer exists.", enfe);
            }
            em.remove(motorcycle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Motorcycle> findMotorcycleEntities() {
        return findMotorcycleEntities(true, -1, -1);
    }

    public List<Motorcycle> findMotorcycleEntities(int maxResults, int firstResult) {
        return findMotorcycleEntities(false, maxResults, firstResult);
    }

    private List<Motorcycle> findMotorcycleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Motorcycle as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Motorcycle findMotorcycle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Motorcycle.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotorcycleCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Motorcycle as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
