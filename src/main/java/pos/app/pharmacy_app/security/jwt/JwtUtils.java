package pos.app.pharmacy_app.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import pos.app.pharmacy_app.security.services.UserDetailsImplementation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtils {
    private Logger logger= LoggerFactory.getLogger(JwtUtils.class);
    @Value("${pos.app.jwtSecret}")
    private String jwtSecrete;
    @Value("${pos.app.jwtExpirationMs}")
    private String jwtExpirationMs;
    @Value("${pos.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromCookie(HttpServletRequest request){
        Cookie cookie= WebUtils.getCookie(request,jwtCookie);
        if (cookie!=null){
            return cookie.getValue();
        }
            else{
                return null;
            }
        }
        public ResponseCookie generateJwtCookie(UserDetailsImplementation userPrincipal){
        String jtw=generateJwtTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie responseCookie= ResponseCookie.from(jwtCookie,jtw).path("/api")
                .maxAge(24*60*60).httpOnly(true).build();
        return responseCookie;
        }
        public String getNameFromUsername(String token){
        return Jwts.parser().setSigningKey(jwtSecrete).parseClaimsJws(token).getBody().getSubject();
        }
    public boolean validateJwtToken(String authToken) throws SignatureException {
        try {
            Jwts.parser().setSigningKey(jwtSecrete).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
    public String generateJwtTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecrete)
                .compact();
    }
    }

