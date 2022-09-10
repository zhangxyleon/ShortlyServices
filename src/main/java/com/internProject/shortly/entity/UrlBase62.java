package com.internProject.shortly.entity;

import javax.persistence.*;

@Entity
@Table(name = "url_base62")
public class UrlBase62 {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="long_url")
    private String longUrl;

    @Column(name="sequence_id")
    private long sequenceId;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(long sequenceId) {
        this.sequenceId = sequenceId;
    }

    @Override
    public String toString() {
        return "UrlBase62{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", sequenceId=" + sequenceId +
                '}';
    }
}
