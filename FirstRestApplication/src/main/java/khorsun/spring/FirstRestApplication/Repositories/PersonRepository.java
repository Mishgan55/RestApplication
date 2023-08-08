package khorsun.spring.FirstRestApplication.Repositories;

import khorsun.spring.FirstRestApplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
