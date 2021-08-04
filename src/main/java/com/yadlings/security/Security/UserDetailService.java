package com.yadlings.security.Security;

import com.yadlings.security.Domain.User;
import com.yadlings.security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return
            userRepository
                    .findByUsername(s)
                    .map(user -> userDetails(
                            grantAuthority(user.getAdmin()),
                            user
                            ))
                    .orElseThrow(()-> new UsernameNotFoundException("User does not exist"));

    }

    private UserDetails userDetails(List<GrantedAuthority> grantAuthority, User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantAuthority);
    }

    private List<GrantedAuthority> grantAuthority(Boolean admin){
        return admin ? AuthorityUtils.createAuthorityList("ADMIN_ROLE"):new ArrayList<>();
    }
}
