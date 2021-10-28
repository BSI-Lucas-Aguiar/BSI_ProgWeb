/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroEmbarcacoes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Administrador extends Usuario{
    
    public Administrador(String login, String senha){
        super(login, senha);
    }
    
    public Administrador(){

    }
    
    public void cadastrarAdministrador(){
        
    }
}
