package model;

import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NodeEntity(label="FireBrigade")
public class FireBrigade {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String motto;

    @DateLong
    private Date created;

    @Relationship(type="EMPLOYS")
    private List<Employs> employs;

    public FireBrigade() {
        this.employs = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto=motto;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created=created;
    }

    public List<Employs> getEmploys() {
        return employs;
    }

    public void setEmploys(List<Employs> employs) {
        this.employs=employs;
    }

    @Override
    public String toString() {
        return "FireBrigade{" +"\n"+
                "id=" + id +"\n"+
                ", motto='" + motto + '\'' +"\n"+
                ", created=" + created +"\n"+
                ", employs=" + employs +"\n"+
                '}';
    }
}
