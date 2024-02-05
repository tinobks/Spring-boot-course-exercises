package com.develhope.tino.es10hal.controllers;

import com.develhope.tino.es10hal.entities.ProgrammingLanguage;
import com.develhope.tino.es10hal.repos.ProgrammingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProgrammingLanguageController {

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @PostMapping("/add")
    public ResponseEntity<ProgrammingLanguage> add(@RequestBody ProgrammingLanguage language) {
        ProgrammingLanguage savedLanguage = programmingLanguageRepository.save(language);
        return ResponseEntity.ok(language);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProgrammingLanguage>> getAll(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "2") int size) {
        List<ProgrammingLanguage> languages = programmingLanguageRepository.findAll();
        return ResponseEntity.ok(languages);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProgrammingLanguage> updateInventor(@PathVariable Long id, @RequestParam String inventor) {
        ProgrammingLanguage existingLanguage = programmingLanguageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));

        existingLanguage.setInventor(inventor);
        ProgrammingLanguage updatedLanguage = programmingLanguageRepository.save(existingLanguage);

        return ResponseEntity.ok(updatedLanguage);
    }
}
