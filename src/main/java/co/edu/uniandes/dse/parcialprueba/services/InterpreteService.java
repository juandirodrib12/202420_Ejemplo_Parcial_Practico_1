package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import co.edu.uniandes.dse.parcialprueba.entities.InterpreteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.InterpreteRepository;

@Slf4j
@Service
public class InterpreteService {

    @Autowired
    private InterpreteRepository interpreteRepository;

    @Transactional
    public InterpreteEntity createInterprete(InterpreteEntity interprete) {
        log.info("Inicia proceso de creación de un intérprete");

        if (interprete.getNombre() == null || interprete.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del intérprete no puede ser nulo o vacío");
        }

        if (interprete.getInstrumento() == null) {
            throw new IllegalArgumentException("El instrumento del intérprete no puede ser nulo");
        }

        if (interprete.getBiografia() == null || interprete.getBiografia().isBlank()) {
            throw new IllegalArgumentException("La biografía del intérprete no puede ser nula o vacía");
        }

        log.info("Creando el intérprete con nombre: {}", interprete.getNombre());
        return interpreteRepository.save(interprete);
    }
}
