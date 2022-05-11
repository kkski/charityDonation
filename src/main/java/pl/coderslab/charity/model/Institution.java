package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="institutions")
@Getter
@Setter
@NoArgsConstructor
//notblank
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotEmpty(message = "Wprowadź wartość")
    @NotNull(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String name;
    @NotEmpty(message = "Wprowadź wartość")
    @NotNull(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String description;



}
