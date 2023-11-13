package com.springsecurity.securityjwt.service;

import com.springsecurity.securityjwt.dto.LoginRequest;
import com.springsecurity.securityjwt.dto.LoginResponce;
import com.springsecurity.securityjwt.entity.Role;
import com.springsecurity.securityjwt.entity.User;
import com.springsecurity.securityjwt.repository.UserRepository;
import com.springsecurity.securityjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findById(username).get();
        if(user !=null){
            return  new org.springframework.security.core.userdetails.User(

                    user.getUaserName(),
                    user.getUserPassword(),
                    getAuthoty(user)
            );

        }else{
            throw new UsernameNotFoundException("User Not Found For This :"+user.getUaserName());
        }



    }

    private Set getAuthoty(User user) {
        Set<SimpleGrantedAuthority> authorities= new HashSet<>();
//        for (Role role : user.getRole()){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//
//        }
        user.getRole().forEach(role->{
                authorities.add( new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
     return authorities;
    }

    public LoginResponce createJwtToken(LoginRequest loginRequest) throws Exception{
       String userName= loginRequest.getUserName();
       String userPassword= loginRequest.getPassword();
       authrnticate(userName,userPassword);
       UserDetails userDetails=loadUserByUsername(userName);
       String newGeneratedToken= jwtUtil.generateToken(userDetails);
       User user= userRepository.findById(userName).get();

       LoginResponce loginResponce= new LoginResponce(
               user,   newGeneratedToken
       );
       return loginResponce;
    }

    private void authrnticate(String userName, String userPassword) throws Exception {
        try{
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDETIALS",e);
        }
    }
}
