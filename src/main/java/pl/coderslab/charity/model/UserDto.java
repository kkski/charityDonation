package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Wartość nie może być pusta")
    private String username;
    @NotBlank(message = "Wartość nie może być pusta")
    private String firstName;
    @NotBlank(message = "Wartość nie może być pusta")
    private String lastName;
    @NotBlank(message = "Wartość nie może być pusta")
    private String email;
    @NotBlank(message = "Wartość nie może być pusta")
    private String password;
}
