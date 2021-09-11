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
public class Lancha extends Embarcacao{
    private String motor;
    private int numPassageiros;
    private boolean PossuiReboque;
    
    public Lancha(){
        
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        this.numPassageiros = numPassageiros;
    }

    public boolean isPossuiReboque() {
        return PossuiReboque;
    }

    public void setPossuiReboque(boolean PossuiReboque) {
        this.PossuiReboque = PossuiReboque;
    }
    
    public void cadastrarLancha(){
        
    }
}
