package pos.app.pharmacy_app.security.webSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pos.app.pharmacy_app.security.jwt.AuthEntryPointJWT;
import pos.app.pharmacy_app.security.jwt.AuthTokenFilter;
import pos.app.pharmacy_app.security.services.UserDetailsImplementation;
import pos.app.pharmacy_app.security.services.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
   private UserDetailsServiceImplementation userDetailService;
   @Autowired
   private AuthEntryPointJWT authEntryPointJWT;
   @Bean
    public AuthTokenFilter authTokenFilter(){
        return new AuthTokenFilter();
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJWT)
                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and().authorizeRequests().antMatchers("api/auth/**").permitAll()
                        .anyRequest().authenticated();
        http.headers().frameOptions().sameOrigin();
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
