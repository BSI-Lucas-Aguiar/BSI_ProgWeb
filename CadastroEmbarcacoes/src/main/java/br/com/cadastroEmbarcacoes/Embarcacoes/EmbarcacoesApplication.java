package br.com.cadastroEmbarcacoes.Embarcacoes;

import br.com.cadastroEmbarcacoes.model.Cliente;
import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.model.TipoEmbarcacaoEnum;
import br.com.cadastroEmbarcacoes.repository.AdministradorRepository;
import br.com.cadastroEmbarcacoes.repository.ClienteRepository;
import br.com.cadastroEmbarcacoes.repository.EmbarcacaoRepository;
import br.com.cadastroEmbarcacoes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbarcacoesApplication implements CommandLineRunner {
        
        @Autowired
        private ClienteRepository clienteRepo;
        @Autowired
        private EmbarcacaoRepository embarcacaoRepo;
        
        
	public static void main(String[] args) {
		SpringApplication.run(EmbarcacoesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        
        //Cliente
        Cliente c1 = new Cliente("Lucas", "199.312.140-40", "lucas@gmail.com", "lucas1", "12345678");
        clienteRepo.save(c1);
        Cliente c2  = new Cliente("Mateus", "730.507.110-27", "mateus@gmail.com", "mateus1", "12345678");
        clienteRepo.save(c2);
        Cliente c3  = new Cliente("Amarildo", "832.932.660-88", "amarildo@gmail.com", "amarildo1", "12345678");
        clienteRepo.save(c3);

        //Administrador
        
        //Embarcações
        Embarcacao e1 = new Embarcacao("Fibrafort", "F420 GC", "Branca", 1000000.00, 4, false, true, TipoEmbarcacaoEnum.Iate, c1);
        embarcacaoRepo.save(e1);
        
    }

}
