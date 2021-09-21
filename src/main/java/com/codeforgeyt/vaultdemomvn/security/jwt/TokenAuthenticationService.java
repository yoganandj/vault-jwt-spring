package com.codeforgeyt.vaultdemomvn.security.jwt;

import com.codeforgeyt.vaultdemomvn.ApiStoreGateway;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 1000*60*60*24*10 ;
    private String secret = "ThisIsASecret";
    private String headerString = "Authorization" ;
    private String tokenPrefix = "Bearer";
    private String api_key = "ApiKey";

    public void addAuthentication(HttpServletResponse response, String username){
        ApiStoreGateway apiStoreGateway = ApiStoreGateway.get();
        Map<String, Object> keyDetails =  apiStoreGateway.getKeyValuePair(username,"").get("data");
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.valueOf((String)keyDetails.get("algorithm")), (String)keyDetails.get("secret"))
                .compact();
        response.addHeader(headerString, tokenPrefix + " "+JWT);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        System.out.println("getAuthentication ----------------------:"+request.getRequestURI() +" : "+request.getServletPath());
        String token = request.getHeader(headerString);
        String apiKey = request.getHeader(api_key);

        if (token != null) {
            ApiStoreGateway apiStoreGateway = ApiStoreGateway.get();
            System.out.println("apiKey --------- : "+apiKey);
            Map<String, Object> keyDetails =  apiStoreGateway.getKeyValuePair(apiKey,"").get("data");
            String username = null;
            // parse the token.
            try {
                username = Jwts.parser()
                        .setSigningKey(((String) keyDetails.get("secret")))
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
                if (username != null) // we managed to retrieve a user
                {
                    System.out.println("username .... validated");
                    return new AuthenticatedUser(username);
                }else{
                    System.out.println("username not .... validated");
                }
            }catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex){
                    System.out.println("Invalid JWT Token  : "+ex);
                    throw new BadCredentialsException("Invalid JWT Token ", ex);
            }catch (ExpiredJwtException expiredEx){
                System.out.println("Expired JWT Token  : "+expiredEx);
                throw new BadCredentialsException("Expired JWT Token", expiredEx);
            }

        }
        return null;
    }
}
