package co.edu.uniandes.dse.parcialprueba.services;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialprueba.entities.InterpreteEntity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@DataJpaTest
@Import(InterpreteService.class)
public class InterpreteServiceTest {

    @Autowired
    private InterpreteService interpreteService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    public void setUp() {
        clearData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from InterpreteEntity").executeUpdate();
    }

    @Test
    public void createInterpreteTest() {
        InterpreteEntity interprete = factory.manufacturePojo(InterpreteEntity.class);
        InterpreteEntity result = interpreteService.createInterprete(interprete);
        assertNotNull(result);

        InterpreteEntity entity = entityManager.find(InterpreteEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(interprete.getNombre(), entity.getNombre());
        assertEquals(interprete.getInstrumento(), entity.getInstrumento());
        assertEquals(interprete.getBiografia(), entity.getBiografia());
    }

    @Test
    public void createInterpreteTestInvalidName() {
        InterpreteEntity interprete = factory.manufacturePojo(InterpreteEntity.class);
        interprete.setNombre(null);

        assertThrows(IllegalArgumentException.class, () -> {
            interpreteService.createInterprete(interprete);
        });
    }
}
