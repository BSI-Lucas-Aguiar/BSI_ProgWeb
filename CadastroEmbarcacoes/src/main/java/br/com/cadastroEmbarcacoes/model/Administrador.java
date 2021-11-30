/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroEmbarcacoes.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Administrador extends Usuario{
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Administrador deve ter pelo menos uma permissão")
    private List<Permissao> permissoes;

    public Administrador(List<Permissao> permissoes, String login, String senha) {
        super(login, senha);
        this.permissoes = permissoes;
    }

    public Administrador(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    public Administrador(){

    }
    
    public void cadastrarAdministrador(){
        
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
}
