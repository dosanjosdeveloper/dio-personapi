package br.com.dosanjosdeveloper.personapi.repository;

import br.com.dosanjosdeveloper.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
