package pucp.dp1.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//API demo
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping(path = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home(){
        return "Hello world";
    }

    @GetMapping(path = "/private", produces = MediaType.APPLICATION_JSON_VALUE)
    public String privateArea(){
        return "Private area";
    }

}
