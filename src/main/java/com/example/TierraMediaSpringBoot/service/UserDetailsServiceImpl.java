package com.example.TierraMediaSpringBoot.service;

import com.example.TierraMediaSpringBoot.models.RoleEntity;
import com.example.TierraMediaSpringBoot.models.UserCredentialEntity;
import com.example.TierraMediaSpringBoot.repositories.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentialEntity userCredentialEntity = userCredentialRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User " + username + "not found."));

        List<RoleEntity> roleList = getRoleList(userCredentialEntity);
        Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(roleList);

        return new User(userCredentialEntity.getUsername(),
                userCredentialEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<RoleEntity> roleList) {
        return roleList.stream()
                .map(roleEntity -> new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getERoleType().name())))
                .collect(Collectors.toSet());
    }

    private List<RoleEntity> getRoleList(UserCredentialEntity userCredentialEntity) {
        return Collections.singletonList(userCredentialEntity.getRole());
    }
}
