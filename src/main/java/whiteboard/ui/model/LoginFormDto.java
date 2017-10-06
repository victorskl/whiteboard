package whiteboard.ui.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class LoginFormDto implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public LoginFormDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
