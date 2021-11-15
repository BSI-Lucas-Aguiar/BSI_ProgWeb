package br.com.cadastroEmbarcacoes.model;

import br.com.cadastroEmbarcacoes.annotation.SenhaValidation;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable{

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario(){
        
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100, unique = true, updatable = false)
    @NotBlank(message = "Insira um nome de Usuário válido.")
    @Length(min = 5, max = 100, message = "O nome de usuário deve ter no mínimo 5 caracteres e no máximo 100.")
    private String login;
    
    @Column(nullable = false, unique = false, updatable = true) //Pretendido gerar hash criptografado de 255 caracteres
    @NotBlank(message = "Insira uma senha válida.")
    @Length(min = 8, max = 8, message = "A senha deve conter exatamente 8 caractere.")
    @SenhaValidation(message = "Senha inválida")
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        hash = 47 * hash + Objects.hashCode(this.id);
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
