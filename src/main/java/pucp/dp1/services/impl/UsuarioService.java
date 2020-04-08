package pucp.dp1.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pucp.dp1.dao.IUser;
import pucp.dp1.model.User;
import pucp.dp1.services.dao.IUsuarioService;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService{

	@Autowired
	private IUser dao;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = dao.findByUsername(username);
		
		if(usuario==null) {
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getDescription()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true,true, true, authorities);
	}

	@Override
	public User save(User user) {
		User usuarioNuevo = new User(user);
		if(usuarioNuevo.getPassword()!=null  && usuarioNuevo.getPassword()!="") {
			String passwordBcrypt = passwordEncoder().encode(usuarioNuevo.getPassword());
			usuarioNuevo.setPassword(passwordBcrypt);
		}else {
			usuarioNuevo.setPassword(null);
		}
		return dao.save(usuarioNuevo);
	}
}
