package com.develhope.tino.es3;

import org.springframework.web.bind.annotation.*;

//scrivi una applicazione web Spring Boot con:
//NameController dove si mappa il parametro name per:
//restituire il nome alla chiamata GET
//restuiture il nome al contrario (es. da John a nhoJ, usando StringBuilder) alla chiamata POST
//testare le chiamate del API endpoint usando Postman
@RestController
@RequestMapping("es3/")
public class NameController {

    @GetMapping("/notReversedName")
    public String getName(@RequestParam String name) {
        return "Name: " + name;
    }

    @PostMapping("/reversedName")
    public String reversedName(@RequestParam String name) {
        String reversed = new StringBuilder(name).reverse().toString();
        return "Reversed name: " + reversed;
    }
}
