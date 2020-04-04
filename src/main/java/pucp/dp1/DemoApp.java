package pucp.dp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
    
    //para probar la app en postman 
    // ---> GET -> localhost:8080/ -> permisos para todos --> Verán hello world  
    // ---> GET -> localhost:8080/private -> necesita permisos --> Verán falta autorización
    	//--> POST -> >localhost:8080/oauth/token -> ingresar parametros username,password y grant_type, pueden ver el archivo WebSecurityConfig para datos 
    	// Antes de mandar revisar en pestaña authorization -> Basic Auth -> Username: frontApp Password: secret 
    	// Se obtiene el token access
    // ---> Intente nuevamente acceder a localhost:8080/private, pero ahora en authorization debe colocar Bearer Token y pegar el token obtenido previamente
    // ---> Deberian ver PRIVATE AREA
    //De esta manera protegeriamos los accesos a nuestro api para que no todo el mundo pueda consultarlo.
}
