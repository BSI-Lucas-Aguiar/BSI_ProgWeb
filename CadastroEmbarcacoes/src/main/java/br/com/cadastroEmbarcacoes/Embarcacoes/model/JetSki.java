/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroEmbarcacoes.Embarcacoes.model;

/**
 *
 * @author lukas
 */
public class JetSki extends Embarcacao{
    private String motor;
    private boolean possuiReboque;
    private boolean possuiCapa;
    
    public JetSki(){
        
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public boolean isPossuiReboque() {
        return possuiReboque;
    }

    public void setPossuiReboque(boolean possuiReboque) {
        this.possuiReboque = possuiReboque;
    }

    public boolean isPossuiCapa() {
        return possuiCapa;
    }

    public void setPossuiCapa(boolean possuiCapa) {
        this.possuiCapa = possuiCapa;
    }
    
    public void cadastrarJetSki(){
        
    }
}
