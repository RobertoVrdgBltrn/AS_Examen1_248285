/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.entity.Cliente;

/**
 *
 * @author rober
 */
public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public List<Cliente> buscarPorNumeroServicio(String criterio) {
        return em.createQuery(
                "SELECT c FROM Cliente c WHERE c.numeroServicio LIKE :crit",
                Cliente.class)
                .setParameter("crit", criterio + "%")
                .getResultList();
    }

    public void guardar(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }
}
