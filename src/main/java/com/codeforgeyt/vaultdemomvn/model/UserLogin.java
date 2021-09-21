package com.codeforgeyt.vaultdemomvn.model;

public class UserLogin {
    private String userId;
    private String password;
    private String apiKey;
    private Long expiration;
    private String tenantId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", expiration=" + expiration +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
