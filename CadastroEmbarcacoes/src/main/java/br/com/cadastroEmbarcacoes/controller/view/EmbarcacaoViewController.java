
package br.com.cadastroEmbarcacoes.controller.view;

import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.model.TipoEmbarcacaoEnum;
import br.com.cadastroEmbarcacoes.service.ClienteService;
import br.com.cadastroEmbarcacoes.service.EmbarcacaoService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/embarcacoes")
public class EmbarcacaoViewController {
    @Autowired
    private EmbarcacaoService service;
    @Autowired
    private ClienteService service2;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("embarcacoes", service.findAll());
        return "embarcacoes";
    }
    
    @GetMapping(path = "/embarcacao")
    public String cadastroEmbarcacao(Model model){
        model.addAttribute("embarcacao", new Embarcacao());
        model.addAttribute("tipoEmbarcacao", TipoEmbarcacaoEnum.values());
        model.addAttribute("clientes", service2.findAll());
        return "formEmbarcacao";
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);         
        return "redirect:/embarcacoes";     
    }
    
    @PostMapping(path="/embarcacao")
    public String save(@Valid @ModelAttribute Embarcacao embarcacao, 
            BindingResult result,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formEmbarcacao";
        }

        embarcacao.setId(null);
        
        try{
            service.save(embarcacao);
            model.addAttribute("msgSucesso", "Embarcacao cadastrada com sucesso!");
            model.addAttribute("embarcacao", new Embarcacao());
            return "formEmbarcacao";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("embarcacao", e.getMessage()));
            return "formEmbarcacao";
        }
    }
    
    @GetMapping(path="/embarcacao/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model){
        model.addAttribute("embarcacao", service.findById(id));
        model.addAttribute("clientes", service2.findAll());
        return "formEmbarcacao";
    }
    
    @PostMapping(path="/embarcacao/{id}")
    public String update(@Valid @ModelAttribute Embarcacao embarcacao, 
            BindingResult result,
            @PathVariable("id") Long id,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formEmbarcacao";
        }
        
        embarcacao.setId(id);
        
        try{
            service.update(embarcacao);
            model.addAttribute("msgSucesso", "Embarcacao atualizado com sucesso!");
            model.addAttribute("embarcacao", embarcacao);
            return "formEmbarcacao";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("embarcacao", e.getMessage()));
            return "formEmbarcacao";
        }
    }
}
