package com.springsecurity.securityjwt.service;

import com.springsecurity.securityjwt.repository.RoleRepository;
import com.springsecurity.securityjwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
@Autowired
private RoleRepository roleRepository;
    public Role createNewRole(Role role){

       return  roleRepository.save(role);
    }
}
