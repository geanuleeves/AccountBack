package com.wildb.account.config;


import com.wildb.account.common.utils.MD5Util;
import com.wildb.account.security.UnauthorizedEntryPoint;
import com.wildb.account.security.UrlAccessDeniedHandler;
import com.wildb.account.security.UrlFilterSecurityInterceptor;
import com.wildb.account.security.UrlUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;


/**
 * <Description> <br>
 *
 * @author henley<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017年1月13日 <br>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UrlUserService urlUserService;

    @Resource
    private UrlFilterSecurityInterceptor urlFilterSecurityInterceptor;

    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Resource
    private UrlAccessDeniedHandler urlAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
//                .antMatchers("/logout").permitAll()
                .antMatchers("/registration").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/static/img/**").permitAll()
                .antMatchers("/static/js/**").permitAll()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("/static/assets/**").permitAll()
                .antMatchers("/static/fonts/**").permitAll()
                .antMatchers("/static/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry)
                .and()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(urlAccessDeniedHandler)
                .and().httpBasic();
//                .and().logout().invalidateHttpSession(true).clearAuthentication(true).permitAll()

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(urlUserService).passwordEncoder(new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
            }
        });

//        auth.inMemoryAuthentication().withUser("wildb").password("wildb").roles("USER");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // ajax 跨域预检命令不能返回401，否则浏览器就报错了。
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//        super.configure(web);
//    }
    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/static/css/**","/static/js/**","/static/img/**","/static/assets/**","/static/fonts/**");
//    }
}