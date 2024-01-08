package com.example.es2;
//Scrivi una applicazione web Spring Boot che alla endpoint GET v2/ciao/Lombardia?nome=Giuseppe
//risponde con un oggetto JSON formato cosi:
//{
//    "nome": "Giuseppe",
//    "provincia": "Lombardia",
//    "saluto": "Ciao Giuseppe, com'è il tempo in Lombardia?"
//}

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class CiaoLombardiaControllerV2 {

    @GetMapping(path = "/ciao/Lombardia")
    public UserV2 ciao(@RequestParam String nome) {
        String provincia = "Lombardia";
        String saluto = "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
        return new UserV2(nome, provincia, saluto);
    }

}
