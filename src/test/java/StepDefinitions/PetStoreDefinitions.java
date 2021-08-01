package StepDefinitions;

import PetStoreActions.CRUDPetStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

public class PetStoreDefinitions
{
    CRUDPetStore crud = new CRUDPetStore();
    List<Map<String, String>> petInfo;
    @Given("a new pet is created")
    public void a_new_pet_is_created(List<Map<String, String>> fieldList) throws JSONException
    {
        petInfo = fieldList;
        crud.createNewPet(fieldList);
    }
    @Then("pet information is verified")
    public void pet_information_is_verified() throws InterruptedException
    {
        crud.getAndValidatePet();
    }
    @When("pet attribute {string} is changed to {string}")
    public void the_petname_is_updated(String attribute, String value) throws JSONException
    {
        crud.updatePetValue(attribute, value);
    }

    @When("pet is deleted")
    public void delete_pet() {
        crud.deletePet();
    }

    @Then("pet can no longer be found")
    public void assertPetDeleted() {
        crud.assertPetDeleted();
    }
}
