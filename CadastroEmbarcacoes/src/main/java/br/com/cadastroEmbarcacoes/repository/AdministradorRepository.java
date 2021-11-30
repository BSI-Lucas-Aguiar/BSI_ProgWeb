package br.com.cadastroEmbarcacoes.repository;

import br.com.cadastroEmbarcacoes.model.Administrador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    @Query("SELECT a FROM Administrador a WHERE a.login = :login")
    public Administrador findByLogin (@Param("login") String login);
}
