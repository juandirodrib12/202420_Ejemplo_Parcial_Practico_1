package co.edu.uniandes.dse.parcialprueba.services;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialprueba.entities.CancionEntity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@DataJpaTest
@Import(CancionService.class)
public class CancionServiceTest {

    @Autowired
    private CancionService cancionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    
    @BeforeEach
    public void setUp() {
        clearData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from CancionEntity").executeUpdate();
    }

    @Test
    public void createCancionTest() {
        CancionEntity cancion = factory.manufacturePojo(CancionEntity.class);
        CancionEntity result = cancionService.createCancion(cancion);
        assertNotNull(result);

        CancionEntity entity = entityManager.find(CancionEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(cancion.getTitulo(), entity.getTitulo());
        assertEquals(cancion.getDuracion(), entity.getDuracion());
        assertEquals(cancion.getGenero(), entity.getGenero());
    }

    @Test
    public void createCancionTestInvalidDuracion() {
        CancionEntity cancion = factory.manufacturePojo(CancionEntity.class);
        cancion.setDuracion(0);

        assertThrows(IllegalArgumentException.class, () -> {
            cancionService.createCancion(cancion);
        });
    }
}
