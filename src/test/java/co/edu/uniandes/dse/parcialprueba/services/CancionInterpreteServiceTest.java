package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import co.edu.uniandes.dse.parcialprueba.entities.CancionEntity;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import co.edu.uniandes.dse.parcialprueba.entities.InterpreteEntity;

@DataJpaTest
@Import(CancionInterpreteService.class)
public class CancionInterpreteServiceTest {

    @Autowired
    private CancionInterpreteService cancionInterpreteService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private CancionEntity cancion;
    private InterpreteEntity interprete;

    @BeforeEach
    public void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from CancionEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from InterpreteEntity").executeUpdate();
    }

    private void insertData() {
        cancion = factory.manufacturePojo(CancionEntity.class);
        entityManager.persist(cancion);
        interprete = factory.manufacturePojo(InterpreteEntity.class);
        entityManager.persist(interprete);
    }

    @Test
    public void addInterpreteToCancionTest() {
        CancionEntity result = cancionInterpreteService.addInterprete(cancion.getId(), interprete.getId());
        assertNotNull(result);
        assertEquals(cancion.getId(), result.getId());
        assertTrue(result.getInterpretes().contains(interprete));
    }

    @Test
    public void addInterpreteToCancionTestInvalidCancion() {
        assertThrows(IllegalArgumentException.class, () -> {
            cancionInterpreteService.addInterprete(500L, interprete.getId());
        });
    }

    @Test
    public void addInterpreteToCancionTestInvalidInterprete() {
        assertThrows(IllegalArgumentException.class, () -> {
            cancionInterpreteService.addInterprete(cancion.getId(), 500L);
        });
    }
}
