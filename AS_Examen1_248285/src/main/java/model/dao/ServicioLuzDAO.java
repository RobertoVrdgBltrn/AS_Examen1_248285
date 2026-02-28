/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import javax.persistence.EntityManager;
import model.entity.Cliente;
import model.entity.ServicioLuz;

/**
 *
 * @author rober
 */
public class ServicioLuzDAO {

    private EntityManager em;

    public ServicioLuzDAO(EntityManager em) {
        this.em = em;
    }

    public ServicioLuz buscarPorCliente(Cliente cliente) {
        return em.createQuery(
                "SELECT s FROM ServicioLuz s WHERE s.cliente = :cliente",
                ServicioLuz.class)
                .setParameter("cliente", cliente)
                .getSingleResult();
    }

    public void actualizar(ServicioLuz servicio) {
        em.getTransaction().begin();
        em.merge(servicio);
        em.getTransaction().commit();
    }
}
