package articles;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ArticleRequest {
    private String oldTitle;
    private String title;
    private String content;
    private String date;

    public String getOldTitle(){
        return oldTitle;
    }
    public void setOldTitle(String oldTitle){
        this.oldTitle = oldTitle;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }


}
