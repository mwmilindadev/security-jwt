package com.springsecurity.securityjwt.service;

import com.springsecurity.securityjwt.entity.Role;
import com.springsecurity.securityjwt.entity.User;
import com.springsecurity.securityjwt.repository.RoleRepository;
import com.springsecurity.securityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public void initRoleAndUser(){
        Role adminRole = new Role();
        Role userRole= new Role();
        if(!roleRepository.existsById("Admin")) {

            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin Role");
            roleRepository.save(adminRole);
        }
        if(!roleRepository.existsById("User")){

            userRole.setRoleName("User");
            userRole.setRoleDescription("UserRole");
            roleRepository.save(userRole);

        }

        if(!userRepository.existsById("admin123")) {
            User user = new User();
            user.setUaserName("admin123");
            user.setUserPassword("admin123");
            user.setUserFirstName("Adminadmin");
            user.setUserLastName("indika");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            user.setRole(adminRoles);
            userRepository.save(user);
        }

        if(!userRepository.existsById("user123")){

            User user = new User();
            user.setUaserName("user123");
            user.setUserPassword("user123");
            user.setUserFirstName("useruser");
            user.setUserLastName("indika");
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);
            userRepository.save(user);

        }

    }
}
