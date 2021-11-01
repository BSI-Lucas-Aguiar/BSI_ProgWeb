package br.com.cadastroEmbarcacoes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Embarcacao implements Serializable{

    public Embarcacao(String marca, String modelo, String cor, Double valor, Integer numPassageiros, Boolean teveManutencao, Boolean aportada, TipoEmbarcacaoEnum tipo, Cliente cliente) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.valor = valor;
        this.numPassageiros = numPassageiros;
        this.teveManutencao = teveManutencao;
        this.aportada = aportada;
        this.tipo = tipo;
        this.cliente = cliente;
    }
    
    public Embarcacao() {

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmbarcacao;
    
    @Column(nullable = false, updatable = false, length = 20)
    @NotBlank(message = "Marca não pode estar em branco")
    @Length(min = 5,max = 20, message = "Marca deve ter no mínimo 5 caracteres e no máximo 20")
    private String marca;
    
    @Column(updatable = false, length = 20)
    @NotBlank(message = "Modelo não pode estar em branco")
    @Length(min = 5,max = 20, message = "Modelo deve ter no mínimo 5 caracteres e no máximo 20")
    private String modelo;
    
    @Column(updatable = false, length = 20)
    @NotBlank(message = "Cor não pode estar em branco")
    @Length(min = 5,max = 20, message = "Cor deve ter no mínimo 5 caracteres e no máximo 20")
    private String cor;
    
    @Column(updatable = true)
    @Min(0)
    @NotNull(message = "Valor não pode estar em branco")
    private Double valor;
    
    @Column(nullable = false)
    @NotNull(message = "Número de passageiros não pode estar em branco")
    @Digits(integer = 3, fraction = 0, message = "Número de passageiros deve ser inteiro e ter até 3 digitos.")
    @Positive
    private Integer numPassageiros;
    
    @Column(nullable = false)
    @NotNull(message = "Teve Manutenção não pode estar em branco")
    private Boolean teveManutencao;
    
    @Column(nullable = false)
    @NotNull(message = "Aportada não pode estar em branco")
    private Boolean aportada;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    @NotNull(message = "A embarcação deve ter um tipo.")
    private TipoEmbarcacaoEnum tipo;

    @ManyToOne
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getIdEmbarcacao() {
        return idEmbarcacao;
    }

    public void setIdEmbarcacao(Long idEmbarcacao) {
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
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idEmbarcacao);
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
