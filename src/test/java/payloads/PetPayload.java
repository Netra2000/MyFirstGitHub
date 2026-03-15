package payloads;


import java.util.HashMap;
import java.util.Map;

public class PetPayload {

    public static Map<String, Object> createPet(int id, String name) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("name", name);
        payload.put("status", "available");

        return payload;
    }
}