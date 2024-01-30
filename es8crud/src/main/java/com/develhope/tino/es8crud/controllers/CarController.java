package com.develhope.tino.es8crud.controllers;

import com.develhope.tino.es8crud.entities.Car;
import com.develhope.tino.es8crud.repos.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    //crea nuova Car
    @PostMapping("/create")
    public Car create(@RequestBody Car car) {
        return carRepository.saveAndFlush(car);
    }

    //restituisce la lista di tutte le Car
    @GetMapping("/getAll")
    public List<Car> getAll() {
        return carRepository.findAll();
    }


    //restituisce una singola Car - se id non è presente in db , restituisce Car vuota
    @GetMapping("get/{id}")
    public Car getById(@PathVariable long id) {
        return carRepository.findById(id).orElse(new Car());
    }

    //aggiorna type della Car specifica, identificata da id e passando query param - se id non è presente in db , restituisce Car vuota
    @PutMapping("updateType/{id}")
    public Car updateType(@PathVariable long id, @RequestParam String newType) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setType(newType);
                    return carRepository.save(car);
                }).orElse(new Car());

        /*for (Car car : carRepository.findAll()) {
            if (id == car.getId()){
                car.setType(newType);
                return car;
            }
        }
        return new Car();*/
    }

    //cancella la Car specifica - se non presente, la risposta deve avere Conflict HTTP status
    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    //cancella tutte le Cars in db
    @DeleteMapping("/delete/all")
    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
