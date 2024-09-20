package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CancionEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private Integer duracion;

    @Column(nullable = false)
    private Genero genero;

    @PodamExclude
    @ManyToMany
    @JoinTable(
        name = "interpretesCanciones",
        joinColumns = @jakarta.persistence.JoinColumn(name = "cancion", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "interprete", referencedColumnName = "id", nullable = false)    
    )
    private List<InterpreteEntity> interpretes = new ArrayList<>();
}
