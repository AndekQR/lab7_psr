package model;

import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type="EMPLOYS")
public class Employs {

    @Id
    @GeneratedValue
    private Long id;
    @DateLong
    private Date startedFrom;
    @StartNode
    private FireBrigade fireBrigade;
    @EndNode
    private Fireman fireman;

    public Long getId() {
        return id;
    }

    public Date getStartedFrom() {
        return startedFrom;
    }

    public void setStartedFrom(Date startedFrom) {
        this.startedFrom=startedFrom;
    }

    public FireBrigade getFireBrigade() {
        return fireBrigade;
    }

    public void setFireBrigade(FireBrigade fireBrigade) {
        this.fireBrigade=fireBrigade;
    }

    public Fireman getFireman() {
        return fireman;
    }

    public void setFireman(Fireman fireman) {
        this.fireman=fireman;
    }

    @Override
    public String toString() {
        return "Employs{" +"\n"+
                "id=" + id +"\n"+
                ", startedFrom=" + startedFrom + "\n"+
                ", fireBrigade=" + fireBrigade.getId() +"\n"+
                ", fireman=" + fireman +"\n"+
                '}';
    }
}
