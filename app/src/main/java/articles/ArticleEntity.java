package articles;

import auth.ProfileEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "article")
public class ArticleEntity {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="title")
    private String title;

    @NotNull
    @Column(name="content")
    private String content;

    @NotNull
    @Column(name="date")
    private String date;

    @OneToOne(optional=false)
    @JoinColumn(name="author_id")
    private ProfileEntity author;

   public ArticleEntity() { }
   public ArticleEntity(String title, String content, String date)
   {
       this.title = title; this.content = content; this.date = date;
   }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public ProfileEntity getAuthor() { return author; }
    public void setAuthor(ProfileEntity author) { this.author = author; }

}


