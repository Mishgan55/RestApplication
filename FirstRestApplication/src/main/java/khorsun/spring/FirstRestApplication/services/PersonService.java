package khorsun.spring.FirstRestApplication.services;

import khorsun.spring.FirstRestApplication.Repositories.PersonRepository;
import khorsun.spring.FirstRestApplication.models.Person;
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

    public Optional<Person> findOne(int id){

        return personRepository.findById(id);
    }
}
