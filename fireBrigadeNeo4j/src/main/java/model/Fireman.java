package model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="Fireman")
public class Fireman {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;
    private String surname;
    private Integer age;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname=surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age=age;
    }

    @Override
    public String toString() {
        return "Fireman{" +"\n"+
                "id=" + id +"\n"+
                ", name='" + name + '\'' +"\n"+
                ", surname='" + surname + '\'' +"\n"+
                ", age=" + age +"\n"+
                '}';
    }
}
