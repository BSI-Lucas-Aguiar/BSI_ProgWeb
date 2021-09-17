
package br.com.cadastroEmbarcacoes.Embarcacoes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente extends Usuario {
    @Column(length = 12, nullable = false)
    private String cpf;
    @Column(length = 100, nullable = false)
    private String nome;
    
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Embarcacao> embarcacao = new ArrayList<>();
    
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
