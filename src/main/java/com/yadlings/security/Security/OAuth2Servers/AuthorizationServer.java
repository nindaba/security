package com.yadlings.security.Security.OAuth2Servers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfiguration) throws  Exception{
        endpointsConfiguration.authenticationManager(authenticationManager);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception{
        clientDetailsServiceConfigurer
                .inMemory()
                .withClient("i7")
                .secret(new BCryptPasswordEncoder().encode("i7"))
                .resourceIds("security")
                .scopes("read","write")
                .authorizedGrantTypes("password");
    }
}
