/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import model.dao.ClienteDAO;
import model.dao.PagoDAO;
import model.dao.ServicioLuzDAO;
import model.entity.Cliente;
import model.entity.Pago;
import model.entity.ServicioLuz;

/**
 *
 * @author rober
 */
public class PagoServicioController {

    private ClienteDAO clienteDAO;
    private ServicioLuzDAO servicioDAO;
    private PagoDAO pagoDAO;

    public PagoServicioController(EntityManager em) {
        this.clienteDAO = new ClienteDAO(em);
        this.servicioDAO = new ServicioLuzDAO(em);
        this.pagoDAO = new PagoDAO(em);
    }

    public List<Cliente> buscarClientes(String criterio) {
        return clienteDAO.buscarPorNumeroServicio(criterio);
    }

    public ServicioLuz obtenerServicio(Cliente cliente) {
        return servicioDAO.buscarPorCliente(cliente);
    }

    public Pago procesarPago(Cliente cliente, String tarjeta) {
        ServicioLuz servicio = servicioDAO.buscarPorCliente(cliente);

        Pago pago = new Pago();
        pago.setCliente(cliente);
        pago.setMonto(servicio.getAdeudo());
        pago.setFecha(LocalDate.now());
        pago.setNumeroTarjeta(tarjeta);

        pagoDAO.guardar(pago);

        servicio.liquidarAdeudo();
        servicioDAO.actualizar(servicio);

        return pago;
    }

}
