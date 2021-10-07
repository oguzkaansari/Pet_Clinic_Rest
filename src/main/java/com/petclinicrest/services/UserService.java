package com.petclinicrest.services;

import com.petclinicrest.entities.Role;
import com.petclinicrest.entities.User;
import com.petclinicrest.repositories.LogRepository;
import com.petclinicrest.repositories.UserRepository;
import com.petclinicrest.utils.Messages;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SimpleUrlLogoutSuccessHandler implements UserDetailsService, LogoutSuccessHandler {

    private static final Logger log = Logger.getLogger(UserService.class);

    final UserRepository uRepo;
    final LogRepository lRepo;
    public UserService(UserRepository uRepo, LogRepository lRepo) {
        this.uRepo = uRepo;
        this.lRepo = lRepo;
    }


    // security login
    @Override
    public UserDetails loadUserByUsername( String email ) {
        UserDetails userDetails = null;
        Optional<User> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if ( oUser.isPresent() ) {
            User us = oUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                    us.getEmail(),
                    us.getPassword(),
                    us.isEnabled(),
                    us.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(us.getRoles()));
            log.info(us.getId() + ":" + Messages.loginSuccessMessage);
        }else {
            log.error(Messages.loginFailMessage);
            throw new UsernameNotFoundException("Kullanıcı adı ya da şifre hatalı");
        }
        return userDetails;
    }


    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
        }
        return authorities;
    }

    public User register( User us ) throws AuthenticationException {

        Optional<User> uOpt = uRepo.findByEmailEqualsAllIgnoreCase(us.getEmail());
        if ( uOpt.isPresent() ) {
            throw new AuthenticationException("Bu kullanıcı daha önce kayıtlı!");
        }
        us.setPassword( encoder().encode( us.getPassword() ) );
        return uRepo.save(us);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException, IOException {
        //final String refererUrl = httpServletRequest.getHeader("Referer");
        log.info(getUserId() + ":" + Messages.logoutSuccessMessage);
        //super.onLogoutSuccess(httpServletRequest, httpServletResponse, authentication );
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
    }


    public Integer getUserId() {

        Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
        String email = aut.getName();
        Optional<User> user = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if(user.isPresent()){
            return user.get().getId();
        }
        return 0;
    }

    public List<Role> getUserRoles(){
        List<Role> roles = new ArrayList<>();
        Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
        String email = aut.getName();
        Optional<User> user = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if(user.isPresent()){
            roles = user.get().getRoles();
        }
        return roles;
    }

    public User getUser(){
        Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
        String email = aut.getName();
        Optional<User> user = uRepo.findByEmailEqualsAllIgnoreCase(email);
        return user.orElseGet(User::new);
    }

    //Gelen id nin o an aktif olan kullanıcıya ait olup olmadığını kontrol eder.
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean checkId(Integer id){
        if(getUserId().equals(id)){
            return true;
        }
        for(Role role : getUserRoles()){
            if(role.getTitle().contains("ADMIN") || role.getTitle().contains("SECRETARY")){
                return true;
            }
        }
        return false;
    }

    public void info(HttpServletRequest req, HttpServletResponse res, com.petclinicrest.entities.Logger logger) {

        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        String email = aut.getName(); // username

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        String ip = request.getRemoteAddr();

        String session = req.getSession().getId();

        Optional<User> user = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if (user.isPresent()) {
            logger.setLname(user.get().getName());
            StringBuilder roles = new StringBuilder();
            for (Role item : user.get().getRoles()) {
                roles.append(item.getTitle()).append(", ");
            }
            if (roles.length() > 0) {
                roles = new StringBuilder(roles.substring(0, roles.length() - 2));
            }
            logger.setLroles(roles.toString());
        }

        logger.setLemail(email);
        logger.setLsessionId(session);
        logger.setLIp(ip);

        logger.setLUrl(req.getRequestURI());
        logger.setLDate(LocalDateTime.now());

        lRepo.save(logger);
    }

}
