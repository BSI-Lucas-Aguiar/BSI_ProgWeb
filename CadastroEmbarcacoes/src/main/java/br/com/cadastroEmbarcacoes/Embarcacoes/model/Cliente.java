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
public class Cliente extends Usuario {
    private String cpf;
    private String nome;
    
    public Cliente(){
        
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCliente() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void cadastrarCliente(){
        
    }
    
}
