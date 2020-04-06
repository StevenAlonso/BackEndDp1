package pucp.dp1.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import pucp.dp1.model.User;

public interface IUser extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
