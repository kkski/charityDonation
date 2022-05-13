package pl.coderslab.charity.services;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class CurrentUser extends User {
    private final pl.coderslab.charity.model.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.charity.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.coderslab.charity.model.User getUser() {return user;}
}