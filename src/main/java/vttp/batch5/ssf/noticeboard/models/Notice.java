package vttp.batch5.ssf.noticeboard.models;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Notice {
    @NotNull(message = "Title cannot be null")
    @Size(min=3, max = 128, message = "Title can only have between 3 and 128 characters.")
    private String title;
    @Email
    @NotNull(message = "Email cannot be null")
    private String poster;
    @NotNull(message = "Date cannot be null")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDate;
    @NotNull(message = "Category cannot be null")
    @Size(min=1,  message = "Must have at least 1 category")
    private String categories;
    @NotNull(message = "Text cannot be null")
    private String text;

    // public Notice() {
    //     this.title = "";
    //     this.poster = "";
    //     this.postDate = new Date();
    //     this.categories = "";
    //     this.text = "";
    // }

    // public Notice(String title, String poster, Date postDate, String categories, String text) {
    //     this.title = title;
    //     this.poster = poster;
    //     this.postDate = postDate;
    //     this.categories = categories;
    //     this.text = text;
    // }
    
    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title;}

    public String getPoster() { return poster;}
    public void setPoster(String poster) {this.poster = poster;}

    public Date getPostDate() { return postDate; }
    public void setPostDate(java.util.Date postDate) {this.postDate = postDate;}

    public String getCategories() { return categories;}
    public void setCategories(String categories) { this.categories = categories;}

    public String getText() { return text;}
    public void setText(String text) { this.text = text;}

    @Override
    public String toString() {
        return "{ " +
        "\"title\": \"" + title + "\"," +
        "\"poster\": \"" + poster + "\"," +
        "\"post_date\": \"" + postDate.getTime() + "\"," +
        "\"categories\": \"" + categories + "\"," +
        "\"text\": \"" +text  + "\"" +
        "}";
    }
}

    



