import com.github.javafaker.Faker;
import model.FireBrigade;

public class ExampleData {

    public static FireBrigade randomFireBrigade() {
        Faker faker = new Faker();
        FireBrigade fireBrigade = new FireBrigade();
        fireBrigade.setMotto(faker.lorem().sentence());
        fireBrigade.setAge(faker.number().numberBetween(1, 60));
        fireBrigade.setNumberOfFiremans(faker.number().numberBetween(1, 15));
        fireBrigade.setNumberOfVehicles(faker.number().numberBetween(1, 10));
        return fireBrigade;
    }

}
