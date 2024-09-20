package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import co.edu.uniandes.dse.parcialprueba.repositories.CancionRepository;
import jakarta.transaction.Transactional;
import co.edu.uniandes.dse.parcialprueba.entities.CancionEntity;

@Slf4j
@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Transactional
    public CancionEntity createCancion(CancionEntity cancion) {
        log.info("Inicia proceso de creación de una canción");

        if (cancion.getTitulo() == null || cancion.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título de la canción no puede ser nulo o vacío");
        }

        if (cancion.getDuracion() == null || cancion.getDuracion() <= 0) {
            throw new IllegalArgumentException("La duración de la canción no puede ser nula o menor o igual a cero");
        }

        if (cancion.getGenero() == null) {
            throw new IllegalArgumentException("El género de la canción no puede ser nulo");
        }

        log.info("Creando la canción con título: {}", cancion.getTitulo());
        return cancionRepository.save(cancion);
    }
}
