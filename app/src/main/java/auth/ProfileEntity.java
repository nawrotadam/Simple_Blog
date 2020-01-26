package auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="profile")
public class ProfileEntity {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="firstname")
    private String firstname;

    @NotNull
    @Column(name="lastname")
    private String lastname;

    @NotNull
    @Column(name="dateofbirth")
    private String dateofbirth;

    public ProfileEntity() { }
    public ProfileEntity(String firstname) { this.firstname = firstname; }
    public ProfileEntity(String username, String password, String firstname, String lastname, String dateofbirth)
    {
        this.username = username; this.password = password; this.firstname = firstname;
        this.lastname = lastname; this.dateofbirth = dateofbirth;
    }

    public Long getId(){ return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassword(){ return password; }
    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getDateOfBirth(){
        return dateofbirth;
    }
    public void setDateOfBirth(String dateofbirth){ this.dateofbirth = dateofbirth; }


}
