package com.example.es1;
//Scrivi una applicazione web Spring Boot che alla endpoint GET v1/ciao?nome=Giuseppe&provincia=Lombardia (perche provincia e non regione non lo so)
//risponde con "Ciao Giuseppe, com'è il tempo in Lombardia?"

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CiaoController {

    @GetMapping(path = "/ciao")
    public String ciao(@RequestParam String nome, @RequestParam String provincia) {
        return "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
    }
}
