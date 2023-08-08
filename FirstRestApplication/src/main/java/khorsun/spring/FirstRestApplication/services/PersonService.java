package khorsun.spring.FirstRestApplication.services;

import khorsun.spring.FirstRestApplication.Repositories.PersonRepository;
import khorsun.spring.FirstRestApplication.models.Person;
import khorsun.spring.FirstRestApplication.utils.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findOne(int id){

        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
}
