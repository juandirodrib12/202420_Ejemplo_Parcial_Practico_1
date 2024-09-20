package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.*;

@Data
@Entity
public class InterpreteEntity extends BaseEntity{

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Instrumento instrumento;

    @Column(nullable = false)
    private String biografia;

    @PodamExclude
    @ManyToMany(mappedBy = "interpretes")
    private List<CancionEntity> canciones = new ArrayList<>();
}
