
package br.com.cadastroEmbarcacoes.controller.view;

import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.model.TipoEmbarcacaoEnum;
import br.com.cadastroEmbarcacoes.service.EmbarcacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/embarcacoes")
public class EmbarcacaoViewController {
    @Autowired
    private EmbarcacaoService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("embarcacoes", service.findAll());
        return "embarcacoes";
    }
    
    @GetMapping(path = "/embarcacao")
    public String cadastroEmbarcacao(Model model){
        model.addAttribute("embarcacao", new Embarcacao());
        model.addAttribute("tipoEmbarcacao", TipoEmbarcacaoEnum.values());
        return "formEmbarcacao";
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);         
        return "redirect:/embarcacoes";     
    }
    
}
