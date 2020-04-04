package pucp.dp1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//API demo
@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String home(){
        return "Hello world";
    }

    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }

}
