package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "secureTokens")
@Getter
@Setter
@NoArgsConstructor
public class SecureToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDate creationDate = LocalDate.now();

    public SecureToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
