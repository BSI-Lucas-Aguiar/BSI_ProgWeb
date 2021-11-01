package br.com.cadastroEmbarcacoes.service;

import br.com.cadastroEmbarcacoes.model.Cliente;
import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.repository.ClienteRepository;
import br.com.cadastroEmbarcacoes.repository.EmbarcacaoRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;
    private EmbarcacaoRepository embarcacaoRepo;
    
    public List<Cliente> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return clienteRepo.findAll(p).toList();
    }
    
    public List<Cliente> findAll(){
        return clienteRepo.findAll();
    }
    
    public Cliente findById(Long id){
        Optional<Cliente> result = clienteRepo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Cliente não encontrado.");
        }
        return result.get();
    }
    
    public Cliente save(Cliente c){
        verificaCpfEmailCadastrado(c.getCpf(), c.getEmail());
        try{
            return clienteRepo.save(c);
        }catch(Exception ex){
            throw new RuntimeException("Falha ao salvar o Cliente");
        }
    }
    
    //O Id não está diretamente implementado na classe sim na super usuário.
    public Cliente update(Cliente c, String email){
        Cliente obj = findById(c.getIdUsuario());
        try{
            c.setEmail(obj.getEmail());
            return clienteRepo.save(c);
        }catch(Exception ex){
            throw new RuntimeException("Falha ao atualizar o Cliente");
        }
    }
    
    public void delete(Long id){
        Cliente obj = findById(id);
        //Verifica se há Embarcações
        //verificaClienteComEmbarcacao(obj);
        
        try{ 
            clienteRepo.delete(obj);
        }catch(Exception ex){
            throw new RuntimeException("Não é possível deletar este Cliente.");
        }
    }
    
    private void verificaCpfEmailCadastrado(String cpf, String email){
        List<Cliente> result = clienteRepo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty()){
            throw new RuntimeException("CPF ou Email inválido");
        }
    }
    /*
    private void verificaClienteComEmbarcacao(Cliente c){
        List<Embarcacao> embarcacoes = ;
        
        for(Embarcacao e: embarcacoes){
            if(Objects.equals(e.getCliente().getIdUsuario(), c.getIdUsuario())){
                throw new RuntimeException("Cliente encontrado com uma embarcação");
            }
        }
    }
    */
}
