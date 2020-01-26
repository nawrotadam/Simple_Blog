package articles;

import auth.ProfileEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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

        ArticleEntity article = new ArticleEntity(articleRequest.getTitle(), articleRequest.getContent(),
                articleRequest.getDate());
        article.setAuthor(author);

        em.persist(article);
        return "index";
    }

    @Transactional
    public String editArticle()
    {
        String oldTitle = articleRequest.getOldTitle();

        ArticleEntity article = em.
                createQuery("Select a from ArticleEntity a where a.title = :oldTitle", ArticleEntity.class).
                setParameter("oldTitle", oldTitle).getSingleResult();

        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setDate(articleRequest.getDate());

        em.merge(article);
        return "index";
    }

    @Transactional
    public List<ArticleEntity> getArticles()
    {
        return em.createQuery("Select a from ArticleEntity a order by date desc", ArticleEntity.class).getResultList();
    }
}
