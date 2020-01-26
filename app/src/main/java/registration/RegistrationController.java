package registration;
import auth.ProfileEntity;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class RegistrationController {
    @Inject
    private RegistrationRequest registrationRequest;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean userExist(String reqUsername, String reqPassword)
    {
        List<ProfileEntity> auctionOwnerList = em.
                createQuery("Select u from ProfileEntity u where u.username = :reqUsername and u.password = :reqPassword", ProfileEntity.class).
                setParameter("reqUsername", reqUsername).setParameter("reqPassword", reqPassword).getResultList();

        return !auctionOwnerList.isEmpty();
    }

    @Transactional
    public String register()
    {
        if(userExist(registrationRequest.getUsername(), registrationRequest.getPassword()))
        {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("already-exists", "Username already exists.");
            return "register.xhtml?faces-redirect=true";
        }
        else
        {
            ProfileEntity user = new ProfileEntity(registrationRequest.getUsername(),
                    registrationRequest.getPassword(), registrationRequest.getFirstname(),
                    registrationRequest.getLastname(), registrationRequest.getDateOfBirth());

            em.persist(user);
        }

        return "index.xhtml?faces-redirect=true";
    }



}
