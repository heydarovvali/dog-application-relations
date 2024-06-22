package az.bcc.dogapplicationspringbootrelations.service;

import az.bcc.dogapplicationspringbootrelations.model.Dog;
import az.bcc.dogapplicationspringbootrelations.model.Person;
import az.bcc.dogapplicationspringbootrelations.repository.DogRepository;
import az.bcc.dogapplicationspringbootrelations.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DogService {
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private PersonRepository personRepository;

    public void addDog(Dog dog) {
        dogRepository.addDog(dog);
    }

    public ArrayList<Dog> getAllDogs() {
        ArrayList<Dog> dogs = dogRepository.getAlldogs();

        for (int i = 0; i < dogs.size(); i++) {
            Dog currentDog = dogs.get(i);

            Person databasePerson = personRepository.getPersonById(currentDog.getOwner().getId());
            currentDog.setOwner(databasePerson);
        }
        return dogs;
    }

    public void updateDog(Long id, Dog dog) {
        dogRepository.updateDog(id, dog);
    }
    public void deleteDog(Long id){
        dogRepository.deleteDog(id);
    }

}
