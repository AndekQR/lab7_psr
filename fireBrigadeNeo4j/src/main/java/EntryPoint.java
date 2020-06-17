import model.FireBrigade;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EntryPoint {
    public static void main(String[] args) {
        Configuration configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "neo4jpassword").build();
        SessionFactory sessionFactory = new SessionFactory(configuration, "model");
        Session session=sessionFactory.openSession();
        FireBrigadeService service = new FireBrigadeService(session);
        FireBrigadeService.FiremanService firemanService = new FireBrigadeService.FiremanService(session);
        service.deleteAll();
        Scanner scanner=new Scanner(System.in);
        int choose;

        do {
            System.out.println("[0] - wyjście,\n " +
                    "[1] - Zapisz randomową stację pożarną,\n " +
                    "[2] - aktualizuj,\n " +
                    "[3] - usuń,\n " +
                    "[4] - pobierz stację po id,\n " +
                    "[5] - pobieranie złożone, po datach,\n " +
                    "[6] - przetwarzanie, inkrementcaja wieku,\n " +
                    "[7] - pobierz wszystkie,\n" +
                    "[8] - usuń wszystkie");
            try {
                choose=Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choose=999;
            }

            switch (choose) {
                case 1: {
                    service.createOrUpdate(ExampleData.randomFireBrigade());
                    break;
                }
                case 2: {
                    System.out.println("Podaj nowe metto straży");
                    String newMotto = scanner.nextLine();
                    System.out.println("Podaj id strazy do aktualizacji");
                    Long id =Long.valueOf(scanner.nextLine());
                    service.updateMotto(newMotto, id);
                    break;
                }
                case 3: {
                    System.out.println("Podaj id strażaka do usunięcia");
                    Long id =Long.valueOf(scanner.nextLine());
                    firemanService.delete(id);
                    break;
                }
                case 4: {
                    System.out.println("Podaj id stacji");
                    Long id =Long.valueOf(scanner.nextLine());
                    System.out.println(service.read(id));
                    break;
                }
                case 5: {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        System.out.println("Podaj od jakiej daty wyświtlic stację (dd/MM/yyyy)");
                        Date from = simpleDateFormat.parse(scanner.nextLine());
                        System.out.println("Podaj do jakiej daty wyświtlic stację (dd/MM/yyyy)");
                        Date to = simpleDateFormat.parse(scanner.nextLine());
                        Iterable<FireBrigade> firebrigadesByCreatedDate=service.getFirebrigadesByCreatedDate(from, to);
                        firebrigadesByCreatedDate.forEach(System.out::println);
                    } catch (ParseException e) {
                        System.out.println("Błędny format daty");
                    }
                    break;
                }
                case 6: {
                    firemanService.incrementFiremansAge();
                    break;
                }
                case 7: {
                    Iterable<FireBrigade> fireBrigades=service.readAll();
                    fireBrigades.forEach(System.out::println);
                    break;
                }
                case 8: {
                    service.deleteAll();
                    break;
                }
                default: {
                    System.out.println("Nieprawidłowa akcja");
                }
            }
        } while (choose != 0);
    }
}
