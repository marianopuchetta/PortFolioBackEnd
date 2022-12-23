package com.portfolio.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.backend.model.Usuario;
import com.portfolio.backend.model.UsuarioDTO;
import com.portfolio.backend.repository.UsuarioRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public Usuario save(UsuarioDTO user) {
		Usuario newUser = new Usuario();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return usuarioRepository.save(newUser);
	}

}
