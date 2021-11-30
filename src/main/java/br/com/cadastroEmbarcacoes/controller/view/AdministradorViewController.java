
package br.com.cadastroEmbarcacoes.controller.view;

import br.com.cadastroEmbarcacoes.model.Administrador;
import br.com.cadastroEmbarcacoes.model.Permissao;
import br.com.cadastroEmbarcacoes.repository.PermissaoRepository;
import br.com.cadastroEmbarcacoes.service.AdministradorService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/administradores")
public class AdministradorViewController {
    @Autowired
    private AdministradorService service;
    @Autowired
    private PermissaoRepository permissaoRepo;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("administradores", service.findAll());
        return "administradores";
    }
    
    @GetMapping(path = "/administrador")
    public String cadastroAdministrador(Model model){
        model.addAttribute("administrador", new Administrador());
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formAdministrador";
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);         
        return "redirect:/administradores";     
    }
    
    @PostMapping(path="/administrador")
    public String save(@Valid @ModelAttribute Administrador administrador, 
            BindingResult result,
            @RequestParam("confirmaSenha") String confirmaSenha,
            Model model){
        //Verifica permissões nulas
        removePermissoesNulas(administrador);
        //Valores a serem retornados
        model.addAttribute("permissoes", permissaoRepo.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formAdministrador";
        }
        
        if(!administrador.getSenha().equals(confirmaSenha)){
            model.addAttribute("msgErros", new ObjectError("administrador", "Campos senha e confirmar senha devem ser iguais."));
            return "formAdministrador";
        }
        
        administrador.setId(null);
        
        try{
            service.save(administrador);
            model.addAttribute("msgSucesso", "Administrador cadastrado com sucesso!");
            model.addAttribute("administrador", new Administrador());
            return "formAdministrador";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }
    
    @GetMapping(path="/administrador/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model){
        model.addAttribute("administrador", service.findById(id));
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formAdministrador";
    }
    
    @PostMapping(path="/administrador/{id}")
    public String update(@Valid @ModelAttribute Administrador administrador, 
            BindingResult result,
            @PathVariable("id") Long id,
            Model model){
        //Verifica permissões nulas
        removePermissoesNulas(administrador);
        //Valores a serem retornados
        model.addAttribute("permissoes", permissaoRepo.findAll());
        
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                list.add(fe);
            }
        }
        
        
        if(!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formAdministrador";
        }
        
        administrador.setId(id);
        
        try{
            service.update(administrador, "", "", "");
            model.addAttribute("msgSucesso", "Administrador atualizado com sucesso!");
            model.addAttribute("administrador", administrador);
            return "formAdministrador";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }
    
    public void removePermissoesNulas(Administrador adm){
        adm.getPermissoes().removeIf((Permissao p) -> {
            return p.getId()==null;
        });
        if(adm.getPermissoes().isEmpty()){
            throw new RuntimeException("Administradores devem ter pelo menos um tipo de permissão.");
        }
    
    }
    
    //----Meus Dados----
    @GetMapping(path = "/meusdados")
    public String getMeusDados(@AuthenticationPrincipal User user, Model model){
        Administrador administrador = service.findByLogin(user.getUsername());
        model.addAttribute("administrador", administrador);
        return "formMeusDados";     
    }
    
    @PostMapping(path="/meusdados")
    public String updateMeusDados(
            @Valid @ModelAttribute Administrador administrador, 
            BindingResult result,
            @AuthenticationPrincipal User user,
            @RequestParam("senhaAtual") String senhaAtual,
            @RequestParam("novaSenha") String novaSenha,
            @RequestParam("confirmaSenha") String confirmaSenha,
            Model model){
        
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha") && !fe.getField().equals("permissoes")){
                list.add(fe);
            }
        }
        
        
        if(!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formMeusDados";
        }
        
        Administrador administradorBD = service.findByLogin(user.getUsername());
        
        if(!administradorBD.getId().equals(administrador.getId())){
            throw new RuntimeException("Acesso negado.");
        }
        
        try{
            administrador.setPermissoes(administradorBD.getPermissoes());
            service.update(administrador, senhaAtual, novaSenha, confirmaSenha);
            model.addAttribute("msgSucesso", "Administrador atualizado com sucesso!");
            model.addAttribute("administrador", administrador);
            return "formMeusDados";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formMeusDados";
        }
    }
}
