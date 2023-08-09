package khorsun.spring.FirstRestApplication.services;

import khorsun.spring.FirstRestApplication.Repositories.PersonRepository;
import khorsun.spring.FirstRestApplication.models.Person;
import khorsun.spring.FirstRestApplication.utils.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
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
    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }
}
