
package br.com.cadastroEmbarcacoes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private AdministradorDetailsService service;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
                .and()
                .authorizeRequests()
                    .antMatchers("/apirest/**").hasRole("ADMIN")
                    .antMatchers("/files/**").hasAnyRole("ADMIN", "CLI")
                    .antMatchers("/clientes/meusdados/**").hasAnyRole("ADMIN", "CLI")
                    .antMatchers("/administradores/").hasRole("ADMIN")
                    .antMatchers("/administradores/**").hasRole("ADMIN")
                    .antMatchers("/**").hasAnyRole("ADMIN", "CLI")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic()
                    .and()
                    .formLogin()
                    .usernameParameter("login");
                    
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
}
