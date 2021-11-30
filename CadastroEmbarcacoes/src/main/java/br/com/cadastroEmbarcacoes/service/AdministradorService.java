package br.com.cadastroEmbarcacoes.service;

import br.com.cadastroEmbarcacoes.exception.NotFoundException;
import br.com.cadastroEmbarcacoes.model.Administrador;
import br.com.cadastroEmbarcacoes.repository.AdministradorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    
    public Administrador findByLogin(String login){
        return administradorRepo.findByLogin(login);
    }
    
    public Administrador findById(Long id){
        Optional<Administrador> result = administradorRepo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Administrador não encontrado.");
        }
        return result.get();
    }
    
    public Administrador save(Administrador a){
        try{
            a.setSenha(new BCryptPasswordEncoder().encode(a.getSenha()));
            return administradorRepo.save(a);
        }catch(Exception ex){
            throw new RuntimeException("Não foi possível salvar o administrador");
        }
    }
    
    public Administrador update(Administrador adm, String senhaAtual, String novaSenha, String confirmaSenha){
        //Verifica se o adm existe
        Administrador obj = findById(adm.getId());
        alterarSenha(obj, senhaAtual, novaSenha, confirmaSenha);
        try{
            adm.setLogin(obj.getLogin());
            adm.setSenha(obj.getSenha());
            return administradorRepo.save(adm);
        }catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if(t instanceof javax.validation.ConstraintViolationException){
                    throw ((javax.validation.ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao modificar administrador." + e);
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
    
    private void alterarSenha(Administrador obj, String senhaAtual, String novaSenha, String confirmaSenha){
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmaSenha.isBlank()){
            if(!crypt.matches(senhaAtual, obj.getSenha())){
                throw new RuntimeException("A senha atual inserida está incorreta!");
            }
            if(!novaSenha.equals(confirmaSenha)){
                throw new RuntimeException("A nova senha e a confirmação não são iguais.");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        }
        
    }
  
}
