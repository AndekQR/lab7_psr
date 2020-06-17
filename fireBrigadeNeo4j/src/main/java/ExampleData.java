import com.github.javafaker.Faker;
import model.Employs;
import model.FireBrigade;
import model.Fireman;

import java.util.concurrent.TimeUnit;

public class ExampleData {

    public static FireBrigade randomFireBrigade() {
        Faker faker = new Faker();
        FireBrigade fireBrigade = new FireBrigade();
        fireBrigade.setCreated(faker.date().past(1300, TimeUnit.DAYS));
        fireBrigade.setMotto(faker.lorem().sentence());

        for (int i=0; i < faker.number().numberBetween(2, 10); i++) {
            Employs employs = new Employs();
            employs.setFireBrigade(fireBrigade);
            employs.setFireman(randomFireMan());
            employs.setStartedFrom(faker.date().past(1250, TimeUnit.DAYS));
            fireBrigade.getEmploys().add(employs);
        }

        return fireBrigade;
    }

    public static Fireman randomFireMan() {
        Faker faker = new Faker();
        Fireman fireman = new Fireman();
        fireman.setAge(faker.number().numberBetween(18, 80));
        fireman.setName(faker.name().firstName());
        fireman.setSurname(faker.name().lastName());
        return fireman;
    }
}
