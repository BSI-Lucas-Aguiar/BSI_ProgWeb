package br.com.cadastroEmbarcacoes.repository;

import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    @Query("SELECT u FROM Usuario u WHERE u.login = :login")
    public List<Usuario> findByLogin (@Param("login") String login);
}
