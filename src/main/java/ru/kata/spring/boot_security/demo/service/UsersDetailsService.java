package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.UsersDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UsersDetails usersDetails;

    @Autowired
    public UsersDetailsService(UserRepository userRepository, RoleRepository roleRepository, UsersDetails usersDetails) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.usersDetails = usersDetails;
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found!");
        return new org.springframework.security.core.userdetails.User(usersDetails.getUsername(), usersDetails.getPassword(), mapRolesToAuthorities(usersDetails.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
