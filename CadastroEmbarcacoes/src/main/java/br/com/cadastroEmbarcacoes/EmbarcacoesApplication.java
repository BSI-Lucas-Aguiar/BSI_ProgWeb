package br.com.cadastroEmbarcacoes;

import br.com.cadastroEmbarcacoes.model.Administrador;
import br.com.cadastroEmbarcacoes.model.Cliente;
import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.model.TipoEmbarcacaoEnum;
import br.com.cadastroEmbarcacoes.repository.AdministradorRepository;
import br.com.cadastroEmbarcacoes.repository.ClienteRepository;
import br.com.cadastroEmbarcacoes.repository.EmbarcacaoRepository;
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
        @Autowired
        private AdministradorRepository administradorRepo;
        
	public static void main(String[] args) {
		SpringApplication.run(EmbarcacoesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        
        //Cliente
        Cliente c1 = new Cliente( "435.030.310-30","Lucas", "lucas1@gmail.com", "lucas1", "12345678");
        clienteRepo.save(c1);
        Cliente c2  = new Cliente("572.779.480-33", "Mateus",  "mateus1@gmail.com", "mateus1", "12345678");
        clienteRepo.save(c2);
        Cliente c3  = new Cliente("351.212.330-94", "Amarildo",  "amarildo2@gmail.com", "amarildo1", "12345678");
        clienteRepo.save(c3);

        //Administrador
        Administrador a1 = new Administrador("admin1", "adm12345");
        administradorRepo.save(a1);
        Administrador a2 = new Administrador("admin2", "adm12345");
        administradorRepo.save(a2);
        
        //Embarcações
        Embarcacao e1 = new Embarcacao("Fibrafort", "F420 GC", "Branca", 4000000.00, 8, false, true, TipoEmbarcacaoEnum.Iate, c1);
        embarcacaoRepo.save(e1);
        Embarcacao e2 = new Embarcacao("Yamaha", "Ventura V215", "Prata", 2500000.00, 4, false, true, TipoEmbarcacaoEnum.Lancha, c2);
        embarcacaoRepo.save(e2);
        Embarcacao e3 = new Embarcacao("Kawasaki", "SX-RTM", "Verde", 56700.00, 2, false, true, TipoEmbarcacaoEnum.JetSki, c3);
        embarcacaoRepo.save(e3);
        
    }

}
