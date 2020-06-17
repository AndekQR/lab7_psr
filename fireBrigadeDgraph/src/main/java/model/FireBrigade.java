package model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FireBrigade {

    private String motto;
    private int age;
    private int numberOfVehicles;
    private int numberOfFiremans;

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto=motto;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles=numberOfVehicles;
    }

    public int getNumberOfFiremans() {
        return numberOfFiremans;
    }

    public void setNumberOfFiremans(int numberOfFiremans) {
        this.numberOfFiremans=numberOfFiremans;
    }

    @Override
    public String toString() {
        return "FireBrigade{" +
                "motto='" + motto + '\'' +
                ", age=" + age +
                ", numberOfVehicles=" + numberOfVehicles +
                ", numberOfFiremans=" + numberOfFiremans +
                '}';
    }
}
