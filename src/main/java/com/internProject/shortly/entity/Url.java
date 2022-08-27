package com.internProject.shortly.entity;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="url")
public class Url  /*implements Serializable*/ {
    // define fields
    //private static final long serialVersionUID = 7156526077883281623L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="long_url")
    private String longUrl;

    @Column(name="short_url", unique=true)
    private String shortUrl;


    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
