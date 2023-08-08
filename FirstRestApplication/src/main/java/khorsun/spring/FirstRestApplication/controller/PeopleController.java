package khorsun.spring.FirstRestApplication.controller;

import khorsun.spring.FirstRestApplication.models.Person;
import khorsun.spring.FirstRestApplication.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<Person> findPeople(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable("id") int id){
        return personService.findOne(id);
    }
}
