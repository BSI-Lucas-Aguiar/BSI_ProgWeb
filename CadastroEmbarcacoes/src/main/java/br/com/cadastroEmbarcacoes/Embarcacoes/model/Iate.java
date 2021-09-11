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
public class Iate extends Embarcacao{
    private int qtdAndar;
    private int qtdQuartos;
    private boolean PossuiPiscina;
    
    public Iate(){
        
    }

    public int getQtdAndar() {
        return qtdAndar;
    }

    public void setQtdAndar(int qtdAndar) {
        this.qtdAndar = qtdAndar;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public boolean isPossuiPiscina() {
        return PossuiPiscina;
    }

    public void setPossuiPiscina(boolean PossuiPiscina) {
        this.PossuiPiscina = PossuiPiscina;
    }
    
}
