package com.develhope.tino.es4;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Names API")
@RestController
@RequestMapping("/v1/names")
public class NameController {

    @Operation(summary = "Get the name", description = "Get the name straight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The name was not found")
        })
    @GetMapping("/straight")
    public String getName(@RequestParam String name) {
        return "Name: " + name;
    }

    @Operation(summary = "Reverse the name", description = "Reverse a name, for example Tino became oniT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The name was not found")
    })
    @PostMapping("/reversed")
    public String reversedName(@RequestParam String name) {
        String reversed = new StringBuilder(name).reverse().toString();
        return "Reversed name: " + reversed;
    }
}
