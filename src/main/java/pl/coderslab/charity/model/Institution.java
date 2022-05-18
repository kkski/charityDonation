package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String name;
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String description;
    @OneToMany(mappedBy = "institution", orphanRemoval = true)
    private List<Donation> donationList;



}
