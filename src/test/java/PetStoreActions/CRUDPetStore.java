package PetStoreActions;

import com.sun.javafx.scene.control.skin.ListViewSkin;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CRUDPetStore
{
    //Get properties like URL
    Properties lp = new LoadProperties().getProperties();
    JSONObject requestParams = new JSONObject();
    String baseURL = lp.getProperty("URL");
    Map<String, String> list = new HashMap<String, String>();
    Response response;


    public void createNewPet(List<Map<String, String>> fieldList) throws JSONException
    {
        list = fieldList.get(0);
        RequestSpecification postRequest = RestAssured.given();
        postRequest.header("Content-Type", "application/json");
        requestParams = generatePostBody();
        postRequest.body(requestParams.toString());
        response = postRequest.post(baseURL);
        Assert.assertEquals(200, response.getStatusCode());
    }

    public void getAndValidatePet() throws InterruptedException
    {
        Thread.sleep(3000);
        RequestSpecification getPetInfo = RestAssured.given();
        getPetInfo.header("Content-Type", "application/json");
        Response response = getPetInfo.get(baseURL + "/" + list.get("Id"));
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jpe = response.jsonPath();
        validatePetStoreBody(jpe);
    }

    public void updatePetValue(String attribute, String value) throws JSONException
    {
        RequestSpecification putNewValue = RestAssured.given();
        putNewValue.header("Content-Type", "application/json");
        requestParams = generatePostBody();
        requestParams.put(attribute, value);
        Map<String, String> map = new HashMap<String, String>(list);
        map.put(attribute, value);
        list = map;
        putNewValue.body(requestParams.toString());
        response = putNewValue.put(baseURL);
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jpe = response.jsonPath();
        validatePetStoreBody(jpe);
    }

    public void deletePet() {
        RequestSpecification deletePet = RestAssured.given();
        Response response = deletePet.delete(baseURL + "/" + list.get("Id"));
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("Pet deleted"));
    }

    public void assertPetDeleted() {
        RequestSpecification petRemoved = RestAssured.given();
        Response response = petRemoved.get(baseURL + "/" + list.get("Id"));
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("Pet not found"));
    }

    public void validatePetStoreBody(JsonPath jpe) {
        Assert.assertEquals(jpe.get("id").toString(), list.get("Id"));
        Assert.assertEquals(jpe.get("category.name"), list.get("CategoryName"));
        Assert.assertEquals(jpe.get("category.id").toString(), list.get("categoryId"));
        Assert.assertEquals(jpe.get("name"), list.get("name"));
        Assert.assertEquals(jpe.get("photoUrls[0]"), list.get("PhotoUrl"));
        Assert.assertEquals(jpe.get("tags[0].name"), list.get("tagName"));
        Assert.assertEquals(jpe.get("tags[0].id").toString(), list.get("tagId"));
        Assert.assertEquals(jpe.get("status"), list.get("status"));
    }

    public JSONObject generatePostBody() throws JSONException
    {
        JSONObject body = new JSONObject();
        JSONObject category = new JSONObject();
        JSONArray photoUrlsArray = new JSONArray();
        JSONArray tagsArray = new JSONArray();
        JSONObject nestedTag = new JSONObject();


        body.put("id", list.get("Id"));
        body.put("name", list.get("name"));
        category.put("id", list.get("categoryId"));
        category.put("name", list.get("CategoryName"));
        body.put("category", category);
        photoUrlsArray.put(list.get("PhotoUrl"));
        body.put("photoUrls", photoUrlsArray);
        nestedTag.put("id", list.get("tagId"));
        nestedTag.put("name", list.get("tagName"));
        tagsArray.put(nestedTag);
        body.put("tags", tagsArray);
        body.put("status", list.get("status"));


        return body;
    }

}
