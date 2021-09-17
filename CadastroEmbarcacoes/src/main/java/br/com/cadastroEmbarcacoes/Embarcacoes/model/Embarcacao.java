/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroEmbarcacoes.Embarcacoes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Embarcacao implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmbarcacao;
    @Column(length = 20, updatable = false)
    private String marca;
    @Column(length = 20, updatable = false)
    private String modelo;
    @Column(length = 20, updatable = false)
    private String cor;
    @Column(updatable = true)
    private double valor;
    @Column(nullable = false)
    private int numPassageiros;
    @Column(nullable = false)
    private boolean teveManutencao;
    @Column(nullable = false)
    private boolean aportada;
    @Column(nullable = false, updatable = false)
    private TipoEmbarcacaoEnum tipo;

    
    
    @ManyToOne
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdEmbarcacao() {
        return idEmbarcacao;
    }

    public void setIdEmbarcacao(int idEmbarcacao) {
        this.idEmbarcacao = idEmbarcacao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getValor() {
        return valor;
    }
    
    public int getNumPassageiros() {
        return numPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        this.numPassageiros = numPassageiros;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isTeveManutencao() {
        return teveManutencao;
    }

    public void setTeveManutencao(boolean teveManutencao) {
        this.teveManutencao = teveManutencao;
    }

    public boolean isAportada() {
        return aportada;
    }

    public void setAportada(boolean aportada) {
        this.aportada = aportada;
    }
    
    public TipoEmbarcacaoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmbarcacaoEnum tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idEmbarcacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Embarcacao other = (Embarcacao) obj;
        if (!Objects.equals(this.idEmbarcacao, other.idEmbarcacao)) {
            return false;
        }
        return true;
    }
}
