/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroEmbarcacoes.Embarcacoes.model;

import java.util.Objects;

/**
 *
 * @author lukas
 */
public abstract class Embarcacao {
    private String idEmbarcacao;
    private String marca;
    private String modelo;
    private String cor;
    private double valor;
    private boolean teveManutencao;
    private boolean aportada;

    public String getIdEmbarcacao() {
        return idEmbarcacao;
    }

    public void setIdEmbarcacao(String idEmbarcacao) {
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
