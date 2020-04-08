package pucp.dp1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pucp.dp1.dao.IUser;
import pucp.dp1.model.User;
import pucp.dp1.services.dao.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{
	
	@Autowired
	private IUser daoUser;

	@Override
	public User findByUsername(String username) {
		return daoUser.findByUsername(username);
	}
	
}
