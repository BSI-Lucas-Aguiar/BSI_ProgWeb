package br.com.cadastroEmbarcacoes.repository;

import br.com.cadastroEmbarcacoes.model.Embarcacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbarcacaoRepository extends JpaRepository<Embarcacao, Long> {
    @Query("SELECT e FROM Embarcacao e WHERE e.cliente = :cliente")
    public List<Embarcacao> findByCliente (@Param("cliente") String cliente);
    
    @Query("SELECT e FROM Embarcacao e WHERE e.marca = :marca OR e.modelo = :modelo")
    public List<Embarcacao> findByMarcaOrModelo (@Param("marca") String marca, @Param("Modelo") String modelo);
    
    //Buscar valores iguais ou maiores ao informado
    @Query("SELECT e FROM Embarcacao e WHERE e.valor >= :valor")
    public List<Embarcacao> findByValorMaior (@Param("valor") Double valor);
    //Buscar valores iguais ou menores ao informado
    @Query("SELECT e FROM Embarcacao e WHERE e.valor <= :valor")
    public List<Embarcacao> findByValorMenor (@Param("valor") Double valor);
    
    /*
    @Query("SELECT DISTINCT(l) FROM Embarcacao e JOIN c.nome WHERE c.nome = :")
    public List<Embarcacao> findByNome (@Param("nome"), String nome);
    */
}