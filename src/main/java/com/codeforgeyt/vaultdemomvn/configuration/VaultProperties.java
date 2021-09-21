package com.codeforgeyt.vaultdemomvn.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class VaultProperties {

    @Value("${spring.cloud.vault.token}")
    private String token;

    @Value("${spring.cloud.vault.host}")
    private String host;

    @Value("${spring.cloud.vault.scheme}")
    private String scheme;

    @Value("${spring.cloud.vault.port}")
    private String port;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }



}
