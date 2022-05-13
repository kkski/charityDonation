package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="institutions")
@Getter
@Setter
@NoArgsConstructor

public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String name;
    @NotBlank(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String description;



}
