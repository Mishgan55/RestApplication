package khorsun.spring.FirstRestApplication.controller;

import khorsun.spring.FirstRestApplication.models.Person;
import khorsun.spring.FirstRestApplication.services.PersonService;
import khorsun.spring.FirstRestApplication.utils.PersonErrorResponse;
import khorsun.spring.FirstRestApplication.utils.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
    public Person findPersonById(@PathVariable("id") int id){
        return personService.findOne(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> hanselException(PersonNotFoundException e){

        PersonErrorResponse response = new PersonErrorResponse("This person doesn't exist", new Date());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
