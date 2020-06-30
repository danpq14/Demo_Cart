package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(name = "userSequence", initialValue = 99999, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;

    @NotEmpty
    private String name;

    @Column(unique = true)
    private String userName;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
