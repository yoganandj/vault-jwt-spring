package com.codeforgeyt.vaultdemomvn.model;

import java.util.List;
import java.util.Set;

public class TenantLogin {

    private String userName;
    private String apiKey;
    private String secretKey;
    private String algorithm;
    private String tenant;
    private String application;
    private List<String> roles;
    private Long expiration;
    private List<String> allowedServices;
    private Set<Integer> permissions;

    public TenantLogin(String userName, String apiKey, String secretKey, String algorithm, String tenant, String application, List<String> roles, Long expiration, List<String> allowedServices, Set<Integer> permissions) {
        this.userName = userName;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.algorithm = algorithm;
        this.tenant = tenant;
        this.application = application;
        this.roles = roles;
        this.expiration = expiration;
        this.allowedServices = allowedServices;
        this.permissions = permissions;
    }

    public TenantLogin() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public List<String> getAllowedServices() {
        return allowedServices;
    }

    public void setAllowedServices(List<String> allowedServices) {
        this.allowedServices = allowedServices;
    }

    public Set<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Integer> permissions) {
        this.permissions = permissions;
    }
}
