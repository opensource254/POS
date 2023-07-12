package pos.app.pharmacy_app.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {
    private Logger logger= LoggerFactory.getLogger(JwtUtils.class);
//    @Value("${pos.app.jwtSecret}")
//    private String jwtSecreteKey;
//    @Value("${pos.app.refreshTokenEpiry}")
//    private Long refreshExpiration;
//    @Value("${pos.app.jwtExpirationMs}")
//    private Long jwtExpiration;
    @Value("${application.security.jwt.secret-key}")
    private String jwtSecreteKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String  extractUsernameFromToken(String token){
        return extractSingleClaim(token,claims -> claims.getSubject());
    }
private Key getSignIngKey(){
    byte[] keysBytes= Decoders.BASE64.decode(jwtSecreteKey);
    return Keys.hmacShaKeyFor(keysBytes);
}
public Claims extractClaims(String token) {
      return Jwts.parserBuilder()
        .setSigningKey(jwtSecreteKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
   }
    public <T>T extractSingleClaim(String token, Function<Claims,T> claimParser){
        final Claims claims=extractClaims(token);
        return claimParser.apply(claims);
    }
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails, long expiresAt){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiresAt))
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String refreshToken(UserDetails userDetails){
        return  generateToken(new HashMap<>(),userDetails,refreshExpiration);
    }
    public String accessToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails, jwtExpiration);
    }
    public boolean tokenIsValid(String token, UserDetails userDetails){
        final  String username= extractUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()))&& isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractSingleClaim(token,date->date.getExpiration());
    }
    }

