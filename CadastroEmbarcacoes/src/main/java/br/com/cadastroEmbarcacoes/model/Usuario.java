package br.com.cadastroEmbarcacoes.model;

import br.com.cadastroEmbarcacoes.annotation.SenhaValidation;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column(nullable = false, length = 100, unique = true, updatable = false)
    @NotBlank(message = "Insira um nome de Usuário válido.")
    @Length(min = 5, max = 100, message = "O nome de usuário deve ter no mínimo 5 caracteres e no máximo 100.")
    private String login;
    
    @Column(nullable = false, unique = false, updatable = true) //Pretendido gerar hash criptografado de 255 caracteres
    @NotBlank(message = "Insira uma senha válida.")
    @Length(min = 8, max = 8, message = "A senha deve conter exatamente 8 caractere.")
    @SenhaValidation(message = "Senha inválida")
    private String senha;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.idUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }
    
}
