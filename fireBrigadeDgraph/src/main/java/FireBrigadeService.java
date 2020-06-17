import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphProto;
import io.dgraph.Helpers;
import io.dgraph.Transaction;
import model.Brigades;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FireBrigadeService {

    private final DgraphClient client;

    public FireBrigadeService(DgraphClient client) {
        this.client=client;
    }

    public <T> void saveObject(T object) {
        String json = this.toJson(object);
        DgraphProto.Mutation build=DgraphProto.Mutation.newBuilder()
                .setSetJson(ByteString.copyFromUtf8(json))
                .build();
        try (Transaction transaction=client.newTransaction()) {
            transaction.mutate(build);
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Brigades getById(String id) {
            String query = "{	brigades(func: uid(0x2)){      motto,    uid,    numberOfFiremans,    numberOfVehicles,    age  }}";
        DgraphProto.Response query1=client.newTransaction().query(query);
        return fromJson(query1.getJson().toStringUtf8());
    }


    public Brigades getAll(){

        String query = "{  brigades(func: has(numberOfFiremans)) {    motto,    uid,    numberOfFiremans,    numberOfVehicles,    age  }}";
        DgraphProto.Response query1=client.newTransaction().query(query);
        return fromJson(query1.getJson().toStringUtf8());
    }

    public Brigades getByNumberOfVehicles(int number) {
        String query = "query brigades($num:int) {  brigades(func: eq(numberOfVehicles, $num)) {    motto,    uid,    numberOfFiremans,    numberOfVehicles,    age  }}";
        Map<String, String> map =ImmutableMap.of("$num", String.valueOf(number));
        DgraphProto.Response response=client.newReadOnlyTransaction().queryWithVars(query, map);
        return fromJson(response.getJson().toStringUtf8());
    }

    public void updateNumberOfVehicles(String firebrigadeid, int vehicles) {
        String query = "{\n" +
                "  set:[\n" +
                "    {\n" +
                "      uid: $id,\n" +
                "      age: $num\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        Map<String, String> map =ImmutableMap.of("$num", String.valueOf(vehicles), "$id", firebrigadeid);
        client.newReadOnlyTransaction().queryWithVars(query, map);
    }

    private <T> String toJson(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    private Brigades fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Brigades.class);
    }

    public void deleteAll() {
        client.alter(DgraphProto.Operation.newBuilder().setDropAll(true).build());
    }
}
