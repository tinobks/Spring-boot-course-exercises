package com.example.es2;
//Scrivi una applicazione web Spring Boot che alla endpoint GET v2/ciao/Lombardia?nome=Giuseppe
//risponde con un oggetto JSON formato cosi:
//{
//    "nome": "Giuseppe",
//    "provincia": "Lombardia",
//    "saluto": "Ciao Giuseppe, com'è il tempo in Lombardia?"
//}

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
public class CiaoLombardiaControllerV2 {

    @GetMapping(path = "/ciao/{provincia}")
    public UserV2 ciao(@PathVariable String provincia, @RequestParam String nome) {
        String saluto = "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
        return new UserV2(nome, provincia, saluto);
    }

}
