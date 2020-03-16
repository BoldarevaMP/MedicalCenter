package unicorn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.UserDAO;
import unicorn.dto.UserDTO;
import unicorn.entity.User;
import unicorn.service.api.UserService;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    public ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            UserDTO userDTO= mapper.map(user, UserDTO.class);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(userDTO.getRole().toString()));
            return new org.springframework.security.core.userdetails.User(userDTO.getEmail(), userDTO.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

    }
}
