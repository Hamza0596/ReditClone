package com.Redit.clone.Service.ServiceImpl;

import java.util.Collection;
import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Redit.clone.Exceptions.UserNameNotFoundException;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.UserRepo;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	@Transactional()
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username).orElseThrow(()->new UserNameNotFoundException("No user name was found with name"+" "+username));
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(), true, true, true, getAuthorities("USER"));
	}
	
	private Collection<?extends GrantedAuthority> getAuthorities (String role){
	return Collections.singletonList(new SimpleGrantedAuthority(role));
	}
}
