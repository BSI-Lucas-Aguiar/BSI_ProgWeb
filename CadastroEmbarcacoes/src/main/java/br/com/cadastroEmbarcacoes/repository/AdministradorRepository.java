package br.com.cadastroEmbarcacoes.repository;

import br.com.cadastroEmbarcacoes.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
}
