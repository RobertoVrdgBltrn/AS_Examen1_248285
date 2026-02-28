/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;

    private LocalDate fecha;

    @Column(nullable = false)
    private String numeroTarjeta;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Pago() {
    }

    public Pago(double monto, LocalDate fecha, String numeroTarjeta, Cliente cliente) {
        this.monto = monto;
        this.fecha = fecha;
        this.numeroTarjeta = numeroTarjeta;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String generarRecibo() {
        return "===== RECIBO DE PAGO =====\n"
                + "Cliente: " + cliente.getNombre() + "\n"
                + "Número de Servicio: " + cliente.getNumeroServicio() + "\n"
                + "Monto pagado: $" + monto + "\n"
                + "Fecha: " + fecha + "\n"
                + "Tarjeta: ****" + numeroTarjeta.substring(numeroTarjeta.length() - 4) + "\n"
                + "==========================";
    }
}
