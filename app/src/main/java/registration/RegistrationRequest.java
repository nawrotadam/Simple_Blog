package registration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RegistrationRequest {
    private String password;
    private String username;
    private String firstname;
    private String lastname;
    private String dateOfBirth;


    public String getPassword(){
        return password;
    }
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
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth){ this.dateOfBirth = dateOfBirth; }

}
