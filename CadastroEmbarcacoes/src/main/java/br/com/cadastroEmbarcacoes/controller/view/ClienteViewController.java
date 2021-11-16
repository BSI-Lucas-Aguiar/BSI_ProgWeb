package br.com.cadastroEmbarcacoes.controller.view;

import br.com.cadastroEmbarcacoes.model.Cliente;
import br.com.cadastroEmbarcacoes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteViewController {
    @Autowired
    private ClienteService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("clientes", service.findAll());
        return "clientes";
    }
    
    @GetMapping(path = "/cliente")
    public String cadastroCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        return "formCliente";
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);         
        return "redirect:/clientes";     
    }
    
}
