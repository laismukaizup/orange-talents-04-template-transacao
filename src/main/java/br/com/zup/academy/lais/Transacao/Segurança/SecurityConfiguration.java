package com.br.zup.academy.lais.proposta.Segurança;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/proposta").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/bloqueioCartao").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/viagem").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/associaPaypal").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
