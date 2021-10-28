package br.com.cadastroEmbarcacoes.service;

import br.com.cadastroEmbarcacoes.model.Administrador;
import br.com.cadastroEmbarcacoes.repository.AdministradorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepo;
    
    public List<Administrador> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return administradorRepo.findAll(p).toList();
    }
    
    public List<Administrador> findAll(){
        return administradorRepo.findAll();
    }
    
    public Administrador findById(Long id){
        Optional<Administrador> result = administradorRepo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Administrador não encontrado.");
        }
        return result.get();
    }
    
    public Administrador save(Administrador a){
        try{
            return administradorRepo.save(a);
        }catch(Exception ex){
            throw new RuntimeException("Não foi possível salvar o administrador");
        }
    }
    
    public Administrador update(Administrador a, String senhaAtual, String novaSenha, String confirmaSenha){
        //Verifica se o adm existe
        Administrador obj = findById(a.getIdUsuario());
        try{
            verificaAlterarSenha(obj, senhaAtual, novaSenha, confirmaSenha);
            return administradorRepo.save(a);
        }catch(Exception ex){
            throw new RuntimeException("Não foi possível alterar a senha.");
        }
        
    }
    
    public void delete(Long id){
        Administrador obj = findById(id);
        try{
            administradorRepo.delete(obj);
        }catch(Exception ex){
            throw new RuntimeException("Falha ao deletar Administrador");
        }
    }
    
    private void verificaAlterarSenha(Administrador obj, String senhaAtual, String novaSenha, String confirmaSenha){
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmaSenha.isBlank()){
            if(senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("A nova senha é igual a senha atual");
            }
            if(novaSenha.equals(confirmaSenha)){
                throw new RuntimeException("A senha e a confirmação não são iguais");
            }
        }
        obj.setSenha(novaSenha);
    }
  
}
