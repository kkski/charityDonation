package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jmx.export.annotation.ManagedNotification;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name="donations")
@Getter
@Setter
@NoArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer quantity;
    @NotEmpty
    @ManyToMany
    private List<Category> categories;
    @NotNull
    @ManyToOne
    private Institution institution;
    @NotEmpty(message = "Wprowadź wartość")
    @NotNull(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String street;
    @NotEmpty(message = "Wprowadź wartość")
    @NotNull(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String city;
    @NotEmpty(message = "Wprowadź wartość")
    @NotNull(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String zipCode;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @NotNull
    private LocalTime pickUpTime;
    @NotBlank(message = "Wprowadź wartość")
    @Size(min=4, message = "Wprowadź minimum 4 litery.")
    private String pickUpComment;

}
