package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialprueba.entities.CancionEntity;
import co.edu.uniandes.dse.parcialprueba.entities.InterpreteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.CancionRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.InterpreteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CancionInterpreteService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private InterpreteRepository interpreteRepository;

    @Transactional
    public CancionEntity addInterprete(Long idCancion, Long idInterprete) {
        log.info("Inicia proceso de agregar un intérprete a una canción");

        if (idCancion == null) {
            throw new IllegalArgumentException("El id de la canción no puede ser nulo");
        }

        if (idInterprete == null) {
            throw new IllegalArgumentException("El id del intérprete no puede ser nulo");
        }

        Optional<CancionEntity> cancion = cancionRepository.findById(idCancion);

        if (cancion.isEmpty()) {
            throw new IllegalArgumentException("La canción con id " + idCancion + " no existe");
        }

        Optional<InterpreteEntity> interprete = interpreteRepository.findById(idInterprete);

        if (interprete.isEmpty()) {
            throw new IllegalArgumentException("El intérprete con id " + idInterprete + " no existe");
        }

        CancionEntity cancionEntity = cancion.get();
        InterpreteEntity interpreteEntity = interprete.get();

        if (cancionEntity.getInterpretes().contains(interpreteEntity)) {
            throw new IllegalArgumentException("El intérprete con id " + idInterprete + " ya está asociado a la canción con id " + idCancion);
        }   

        cancionEntity.getInterpretes().add(interpreteEntity);
        log.info("Agregando el intérprete con id {} a la canción con id {}", idInterprete, idCancion);
        return cancionRepository.save(cancionEntity);
    }
}
