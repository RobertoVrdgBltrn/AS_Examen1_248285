/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import javax.persistence.EntityManager;
import model.entity.Pago;

/**
 *
 * @author rober
 */
public class PagoDAO {

    private EntityManager em;

    public PagoDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Pago pago) {
        em.getTransaction().begin();
        em.persist(pago);
        em.getTransaction().commit();
    }
}
