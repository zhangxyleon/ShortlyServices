package com.internProject.shortly.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String role;

    private String email;

    private String pwd;

//    @OneToMany(mappedBy = "User", fetch =FetchType.LAZY)
//    private List<Url> urls;
//
//    @OneToMany(mappedBy = "User", fetch =FetchType.LAZY)
//    private List<UrlBase62> urlBase62s;

    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    private Set<Authority> authorities;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

//    public List<Url> getUrls() {
//        return urls;
//    }
//
//    public void setUrls(List<Url> urls) {
//        urls = urls;
//    }
//
//    public List<UrlBase62> getUrlBase62s() {
//        return urlBase62s;
//    }
//
//    public void setUrlBase62s(List<UrlBase62> urlBase62s) {
//        urlBase62s = urlBase62s;
//    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //add convenience methods for adding Url

//    public void add(Url url) {
//
//        if(urls == null) {
//            urls = new ArrayList<>();
//        }
//
//        urls.add(url);
//
//        url.setUser(this);
//
//    }
}
