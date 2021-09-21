package com.codeforgeyt.vaultdemomvn.configuration;

import com.codeforgeyt.vaultdemomvn.ApiStoreGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.core.VaultTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class VaultConfiguration extends AbstractVaultConfiguration {

    @Autowired
    private VaultProperties vaultProperties;

    @Autowired
    private VaultTemplate vaultTemplate;

    public VaultConfiguration() {

    }

    public VaultConfiguration(VaultProperties vaultProperties) {
        this.vaultProperties = vaultProperties;
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        URI uri = null;
        try {
            uri = new URI(String.format("%s://%s:%s/%s/", new Object[]{ this.vaultProperties.getScheme(), this.vaultProperties.getHost(), Integer.valueOf(this.vaultProperties.getPort()), "v1" }));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return VaultEndpoint.from(uri);
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return(ClientAuthentication) new TokenAuthentication(this.vaultProperties.getToken());
    }

    @PostConstruct
    public void initBeans(){
        ApiStoreGateway.createInstance(this.vaultTemplate);
    }

    public void show(){
        System.out.println("show -----------------------");
    }
}
