
package br.com.cadastroEmbarcacoes.repository;

import br.com.cadastroEmbarcacoes.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
