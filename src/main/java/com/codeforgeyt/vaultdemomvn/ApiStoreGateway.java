package com.codeforgeyt.vaultdemomvn;

import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import java.util.Map;

public class ApiStoreGateway {
    private static ApiStoreGateway apiStoreGateway;
    private VaultTemplate vaultTemplate;

    private ApiStoreGateway(VaultTemplate vaultTemplate){
        this.vaultTemplate = vaultTemplate;
    }

    public static ApiStoreGateway get(){
        if(apiStoreGateway == null){
            throw new IllegalArgumentException("Vault Service not initialized properly");
        }
        return apiStoreGateway;
    }

    public static ApiStoreGateway createInstance(VaultTemplate vaultTemplate) {
        System.out.println("Initializing ApiStore Gateway --------------------------------");
        apiStoreGateway = new ApiStoreGateway(vaultTemplate);
        return apiStoreGateway;
    }

    public Map<String, Map<String, Object>> getKeyValuePair(String key, String info){
        VaultResponseSupport<Map> response = this.vaultTemplate.read("secret/data/"+key,Map.class);
        return (response!=null) ? (Map<String, Map<String, Object>>) response.getData() : null;
    }
}
