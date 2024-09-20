package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniandes.dse.parcialprueba.entities.InterpreteEntity;

@Repository
public interface InterpreteRepository extends JpaRepository<InterpreteEntity, Long> {

}
