package tests;


import client.APIClient;
import endpoint.Routes;
import payloads.PetPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetTests {

    int petId = 1111;

    @Test
    public void fullCrudFlow() {

        // CREATE
        Response createRes = APIClient.post(
                Routes.CREATE_PET,
                PetPayload.createPet(petId, "AmbikaPet"));

        Assert.assertEquals(createRes.getStatusCode(), 200);

        // GET
        Response getRes = APIClient.get(
                Routes.GET_PET,
                petId);

        Assert.assertEquals(getRes.getStatusCode(), 200);

        // UPDATE
        Response updateRes = APIClient.put(
                Routes.UPDATE_PET,
                PetPayload.createPet(petId, "UpdatedPet"));

        Assert.assertEquals(updateRes.getStatusCode(), 200);

        // DELETE
        Response deleteRes = APIClient.delete(
                Routes.DELETE_PET,
                petId);

        Assert.assertEquals(deleteRes.getStatusCode(), 200);
    }
}