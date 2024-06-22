package az.bcc.dogapplicationspringbootrelations.service;

import az.bcc.dogapplicationspringbootrelations.model.Dog;
import az.bcc.dogapplicationspringbootrelations.model.Person;
import az.bcc.dogapplicationspringbootrelations.repository.DogRepository;
import az.bcc.dogapplicationspringbootrelations.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DogRepository dogRepository;

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public ArrayList<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public void deletePerson(Long id){
      Dog personDog = dogRepository.findDogByOwnerId(id);  // bizim elimizde insanin id si var, biz bu id ile insana aid olan iti tapiriq
       // System.out.println(personDog);
     Person person = new Person(); // yeni bosh insan obyekti qurulur,meqsedimiz sifirlanmis sahib qurmaqdir.
      personDog.setOwner(person);// bizim evvelki veziyyetde insanin id-ne aid olan it var idi.Biz insani silmek isteyirik lakin insana aid it olduguna gore bunu ede bilmirik ona gore iti sahibsiz edirik
      dogRepository.updateDog(personDog.getId(),personDog);// hal hazirda bizde sahibsiz it var bu melumatlari databaseye yaziriq, artiq insan itden asili deyil
      personRepository.deletePerson(id);// asililiqi qirdiqdan sonra rahat insani sile bilerik/


    }
}
