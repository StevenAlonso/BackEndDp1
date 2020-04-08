package pucp.dp1.services.dao;

import pucp.dp1.model.User;

public interface IUsuarioService {
	
	public User findByUsername(String username);
}
