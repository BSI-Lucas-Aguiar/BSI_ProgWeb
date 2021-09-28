/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroEmbarcacoes.Embarcacoes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Embarcacao implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmbarcacao;
    
    @Column(nullable = false, updatable = false, length = 20)
    @NotBlank(message = "Marca não pode estar em branco")
    @Length(min = 5, message = "Deve ter no mínimo 5 caracteres")
    @Length(max = 20, message = "Deve ter no máximo 20 caracteres")
    private String marca;
    
    @Column(updatable = false, length = 20)
    @NotBlank(message = "Modelo não pode estar em branco")
    @Length(min = 5, message = "Deve ter no mínimo 5 caracteres")
    @Length(max = 20, message = "Deve ter no máximo 20 caracteres")
    private String modelo;
    
    @Column(updatable = false, length = 20)
    @NotBlank(message = "Cor não pode estar em branco")
    @Length(min = 5, message = "Deve ter no mínimo 5 caracteres")
    @Length(max = 20, message = "Deve ter no máximo 20 caracteres")
    private String cor;
    
    @Column(updatable = true)
    @Min(0)
    @NotNull(message = "Valor não pode estar em branco")
    private double valor;
    
    @Column(nullable = false)
    @NotNull(message = "Número de Passageiros não pode estar em branco")
    @Digits(integer = 3, fraction = 0, message = "Deve ser inteiro e ter até 3 digitos.")
    private int numPassageiros;
    
    @Column(nullable = false)
    @NotNull(message = "Teve Manutenção não pode estar em branco")
    private boolean teveManutencao;
    
    @Column(nullable = false)
    @NotNull(message = "Aportada não pode estar em branco")
    private boolean aportada;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    @NotNull(message = "A embarcação deve ter um tipo.")
    private TipoEmbarcacaoEnum tipo;

    @ManyToOne
    @Valid
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
