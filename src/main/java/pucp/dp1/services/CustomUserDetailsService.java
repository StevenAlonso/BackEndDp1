package pucp.dp1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pucp.dp1.interfaces.IUser;
import pucp.dp1.model.CustomUserDetails;
import pucp.dp1.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private IUser user;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = user.findByUsername(username);
		
		if(usuario==null) {
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true,true, true, authorities);
	}
}
