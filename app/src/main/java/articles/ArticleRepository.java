package articles;

import auth.ProfileEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class ArticleRepository {

    @Inject
    ArticleRequest articleRequest;

    @Inject
    private HttpServletRequest request;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String addArticle()
    {
        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        ProfileEntity author = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getSingleResult();

        Date localDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String date = dateFormat.format(localDate);

        ArticleEntity article = new ArticleEntity(articleRequest.getTitle(), articleRequest.getContent(), date);
        article.setAuthor(author);

        em.persist(article);
        return "index.xhtml?faces-redirect=true";
    }

    @Transactional
    public String editArticle()
    {
        Long articleId = articleRequest.getArticleId();

        ArticleEntity article = em.
                createQuery("Select a from ArticleEntity a where a.id = :articleId", ArticleEntity.class).
                setParameter("articleId", articleId).getSingleResult();

        Date localDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String newDate = dateFormat.format(localDate);

        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setDate(newDate);

        em.merge(article);
        return "index.xhtml?faces-redirect=true";
    }

    @Transactional
    public boolean isItYourArticle(Long requestedId)
    {
        if(requestedId == null) {return false;}

        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        ProfileEntity articleAuthor = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getSingleResult();

        List<ArticleEntity> article = em.
                createQuery("Select a from ArticleEntity a where a.id = :requestedId and a.author = :articleAuthor", ArticleEntity.class).
                setParameter("requestedId", requestedId).setParameter("articleAuthor",articleAuthor).getResultList();

        return !article.isEmpty();
    }

    @Transactional
    public List<ArticleEntity> getArticles()
    {
        return em.createQuery("Select a from ArticleEntity a order by date desc", ArticleEntity.class).getResultList();
    }
}
