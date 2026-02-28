/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "servicios_luz")
public class ServicioLuz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double consumoKwh;

    private double adeudo;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;

    public ServicioLuz() {
    }

    public ServicioLuz(double consumoKwh, double adeudo, Cliente cliente) {
        this.consumoKwh = consumoKwh;
        this.adeudo = adeudo;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public double getConsumoKwh() {
        return consumoKwh;
    }

    public double getAdeudo() {
        return adeudo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setConsumoKwh(double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public void setAdeudo(double adeudo) {
        this.adeudo = adeudo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void liquidarAdeudo() {
        this.adeudo = 0;
    }
}
