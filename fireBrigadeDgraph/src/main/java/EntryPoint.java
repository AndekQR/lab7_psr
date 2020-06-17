import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.Brigades;
import model.FireBrigade;

import java.util.Optional;
import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {

        ManagedChannel channel =
                ManagedChannelBuilder.forAddress("localhost", 9080).usePlaintext().build();
        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);
        DgraphClient dgraphClient = new DgraphClient(stub);

        FireBrigadeService service = new FireBrigadeService(dgraphClient);
        Scanner scanner=new Scanner(System.in);
        int choose;

        do {
            System.out.println("[0] - wyjście,\n " +
                    "[1] - Zapisz randomową stację pożarną,\n " +
                    "[2] - aktualizuj,\n " +
                    "[3] - usuń,\n " +
                    "[4] - pobierz stację po id,\n " +
                    "[5] - pobieranie złożone,\n " +
                    "[6] - przetwarzanie, inkrementcaja wieku,\n " +
                    "[8] - usuń wszystkie,\n" +
                    "[9] - pobierz wszystkie");
            try {
                choose=Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choose=999;
            }

            switch (choose) {
                case 1: {
                    service.saveObject(ExampleData.randomFireBrigade());
                    break;
                }
                case 2: {
                    System.out.println("Id straży do akualizacji");
                    String id = scanner.nextLine();
                    System.out.println("Nowa liczba pojazdów");
                    int num = Integer.parseInt(scanner.nextLine());
                    service.updateNumberOfVehicles(id, num);
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    System.out.println("Podaj id");
                    String id = scanner.nextLine();
                    Optional<FireBrigade> first=service.getById(id).getBrigades().stream().findFirst();
                    if (first.isPresent()){
                        System.out.println(first);
                    } else {
                        System.out.println("Brak");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Podaj liczbę pojazdów");
                    int number =Integer.parseInt(scanner.nextLine());
                    Brigades byNumberOfVehicles=service.getByNumberOfVehicles(number);
                    byNumberOfVehicles.getBrigades().forEach(System.out::println);
                    break;
                }
                case 6: {
                    break;
                }
                case 8: {
                    service.deleteAll();
                    break;
                }
                case 9: {
                    service.getAll().getBrigades().forEach(System.out::println);
                }
                default: {
                    System.out.println("Nieprawidłowa akcja");
                }
            }
        } while (choose != 0);
    }
}
