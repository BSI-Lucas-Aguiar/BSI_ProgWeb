package br.com.cadastroEmbarcacoes.security;

import br.com.cadastroEmbarcacoes.model.Administrador;
import br.com.cadastroEmbarcacoes.model.Permissao;
import br.com.cadastroEmbarcacoes.repository.AdministradorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorDetailsService implements UserDetailsService{
    
    @Autowired
    private AdministradorRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Administrador adm = repo.findByLogin(login);
        if(adm == null){
            throw new UsernameNotFoundException("Administrador não encontrado com esse Login. "+login);
        }
        return new User(adm.getLogin(), adm.getSenha(), getAuthorities(adm.getPermissoes()));
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista){
        List<GrantedAuthority> l = new ArrayList<>();
        for(Permissao p : lista){ //ADM CLI
            l.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        }
        return l;
    }
}
