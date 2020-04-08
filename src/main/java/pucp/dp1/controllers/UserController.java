package pucp.dp1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pucp.dp1.model.User;
import pucp.dp1.model.response.Estado;
import pucp.dp1.model.response.ResponseObject;
import pucp.dp1.services.impl.UsuarioService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@PostMapping(path = "/usuarios/guardar",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> guardarUsuario( @Valid @RequestBody User user) {
		ResponseObject response = new ResponseObject();
		try {
			User usuarioNuevo = new User(user);
			this.usuarioService.save(usuarioNuevo);
			response.setEstado(Estado.OK);
			return new ResponseEntity<ResponseObject>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setError(1, "Error", e.getMessage());
			response.setEstado(Estado.ERROR);
			return new ResponseEntity<ResponseObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/usuarios/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> consultarUsuario(@PathVariable("username") String username) {
		ResponseObject response = new ResponseObject();
		try {
			UserDetails lista = this.usuarioService.loadUserByUsername(username);
			response.setResultado(lista);
			response.setEstado(Estado.OK);
			return new ResponseEntity<ResponseObject>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setError(1, "Error", e.getMessage());
			response.setEstado(Estado.ERROR);
			return new ResponseEntity<ResponseObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/usuarios/datos/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> consultarDatosUsuario(@PathVariable("username") String username) {
		ResponseObject response = new ResponseObject();
		try {
			User lista = this.usuarioService.findByUsername(username);
			response.setResultado(lista);
			response.setEstado(Estado.OK);
			return new ResponseEntity<ResponseObject>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.setError(1, "Error", e.getMessage());
			response.setEstado(Estado.ERROR);
			return new ResponseEntity<ResponseObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
