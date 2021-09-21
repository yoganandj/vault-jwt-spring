package com.codeforgeyt.vaultdemomvn.service;

import com.codeforgeyt.vaultdemomvn.ApiStoreGateway;
import com.codeforgeyt.vaultdemomvn.model.TenantLogin;
import com.codeforgeyt.vaultdemomvn.model.UserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class SecurityServiceImpl {

    private static final String TOKEN = "token";
    private static final String SERVICES = "services";
    private static final String APIKEY = "apiKey";
    private static final String USER_ID = "userId";
    private static final String EXPIRATION = "expiration";
    private static final String TOKEN_ISSUER = "home";
    private static final String KEY = "key";
    private static final String SECRET = "secret";
    private static final String ALGORITHM = "algorithm";


    public Map<String, Object> login(UserLogin login, HttpServletResponse httpServletResponse){

        Map<String, Object> responseDtls = internalGenerateJwtTokenForUser(login);



        return  responseDtls;

    }

    private Map<String, Object> internalGenerateJwtTokenForUser(UserLogin login) {
        TenantLogin tenantLogin = toTenantLogin(login);
        String token = generateJWTToken(tenantLogin);
        Map<String, Object> responseDtls = new HashMap<>();
        responseDtls.put(TOKEN, token);
        responseDtls.put(APIKEY, tenantLogin.getApiKey());
        responseDtls.put(SERVICES, tenantLogin.getAllowedServices());
        return responseDtls;
    }

    private String generateJWTToken(TenantLogin tenantLogin) {
        return generateJWTToken(tenantLogin, Boolean.TRUE);
    }

    private String generateJWTToken(TenantLogin tenantLogin, Boolean aTrue) {
        Long start = System.currentTimeMillis();
        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims();
        claims.setSubject(tenantLogin.getUserName());
        claims.setIssuer(TOKEN_ISSUER);

        String token = null;

        long jwtTokenExpiration = 1233L;
        token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(TOKEN_ISSUER)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.plusMinutes(jwtTokenExpiration).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.valueOf(tenantLogin.getAlgorithm()) ,tenantLogin.getSecretKey())
                .compact();

        return token;
    }

    private TenantLogin toTenantLogin(UserLogin userLogin) {
        TenantLogin tenantLogin = new TenantLogin();
        tenantLogin.setUserName(userLogin.getUserId());
        tenantLogin.setExpiration(userLogin.getExpiration());

        populateTenantLoginFromTenantId(userLogin, tenantLogin);

        return tenantLogin;
    }

    private void populateTenantLoginFromTenantId(UserLogin userLogin, TenantLogin tenantLogin) {
        tenantLogin.setTenant(userLogin.getTenantId());
        populateTenantLogin(userLogin.getTenantId(), tenantLogin);
    }

    private void populateTenantLogin(String tenantId, TenantLogin tenantLogin) {

        ApiStoreGateway apiStoreGateway = ApiStoreGateway.get();
        Map<String, Map<String, Object>> map =  apiStoreGateway.getKeyValuePair(tenantId,"");
        System.out.println(map);

        Map<String, Object> preferredKeyDetail = map.get("data");
        System.out.println("preferredKeyDetail : "+preferredKeyDetail);
        tenantLogin.setApiKey((String) preferredKeyDetail.get(KEY));
        tenantLogin.setSecretKey((String) preferredKeyDetail.get(SECRET));
        tenantLogin.setAlgorithm((String) preferredKeyDetail.get(ALGORITHM));
        tenantLogin.setAllowedServices((List<String>) preferredKeyDetail.get(SERVICES));

    }
}
