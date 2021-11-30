
package br.com.cadastroEmbarcacoes.service;

import br.com.cadastroEmbarcacoes.exception.NotFoundException;
import br.com.cadastroEmbarcacoes.model.Cliente;
import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.repository.EmbarcacaoRepository;
import java.util.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

    
@Service
public class EmbarcacaoService {
    @Autowired
    private EmbarcacaoRepository repo;
    
    public List<Embarcacao> findAll(int page, int  size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Embarcacao> findAll(){
        return repo.findAll();
    }
    
    public Embarcacao findById(Long id){
        Optional<Embarcacao> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Embarcacao não encontrada.");
        }
        return result.get();
    }
    
    public Embarcacao save(Embarcacao e){
        try{
            return repo.save(e);
        }catch(Exception ex){
            throw new RuntimeException("Falha ao Salvar a Embarcação");
        }
    }
    
    public Embarcacao update(Embarcacao e){
        try{
           return repo.save(e); 
        }catch(Exception ex){
            throw new RuntimeException("Falha ao atualizar as embarcações.");
        }
        
    }
    
    public void delete(Long id){
        Embarcacao obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception ex){
            throw new RuntimeException("Falha ao deletar Embarcação.");
        }
        
    }
    
    /*
    private void verificaExclusaoCliente(List<Embarcacao> clientes){
        for(Embarcacao e :clientes){
            if(!e.getCliente().isEmpty())
                throw new RuntimeException("Não é possível excluir Embarcações vinculadas a clientes.");
        }
    }
    */
}
