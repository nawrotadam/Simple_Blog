package login;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private LoginRequest loginRequest;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private HttpServletRequest request;

    @Transactional
    public String login()
    {
        String reqUsername = loginRequest.getUsername();
        String reqPassword = loginRequest.getPassword();

        String correctPassword =  em.
                createQuery("Select u.password from ProfileEntity u where u.username = :reqUsername", String.class).
                setParameter("reqUsername", reqUsername).getSingleResult();

        if(reqPassword.equals(correctPassword))
        {
            var session = request.getSession(true);
            session.setAttribute("username", reqUsername);
            return "/index.xhtml?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error-message", "Incorrect username or password");
            return "/login.xhtml?faces-redirect=true";
        }
    }

}
