package az.bcc.dogapplicationspringbootrelations.controller;

import az.bcc.dogapplicationspringbootrelations.model.Dog;
import az.bcc.dogapplicationspringbootrelations.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DogController {
    @Autowired
    private DogService dogService;

    @PostMapping("/addDog")
    public ResponseEntity<String> addDog(@RequestBody Dog dog) {
        System.out.println(dog);
        dogService.addDog(dog);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dog added");
    }

    @GetMapping("/getAllDogs")
    public ResponseEntity<ArrayList<Dog>> getAllDogs() {
        return ResponseEntity.ok(dogService.getAllDogs());
    }

    @PutMapping("/updateDog")
    public ResponseEntity<String> updateDog(@RequestBody Dog dog) {
        dogService.updateDog(dog.getId(), dog);
        return ResponseEntity.ok("Dog updated succesfly");
    }

    @DeleteMapping("/deleteDog/{id}")
    public ResponseEntity<String> deleteDog(@PathVariable Long id) {
        return ResponseEntity.ok("Dog deleyted");
    }

}
