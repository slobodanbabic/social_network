package com.slobodanbabic.socialnetwork.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.slobodanbabic.socialnetwork.entity.Role;
import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.repository.UserRepository;


@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	  private UserRepository userRepository;

	  @Override	 
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		  User user = userRepository.findByEmail(email);
		 
		if (user == null) {
		  throw new UsernameNotFoundException("email: " + email+ "not found");
		} else {			 
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for(Role role: user.getRoles())
				grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		
			return new org.springframework.security.core.userdetails.User(
				  user.getEmail(),
				  user.getPassword(),				  
				  grantedAuthorities);
		}
	  }
	}

