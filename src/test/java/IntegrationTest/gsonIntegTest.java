package IntegrationTest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
public class gsonIntegTest {

    @Test
    public void JsonPost() {
        String someRandoString = String.format("%1$TH%1$TM%1$TS", new Date());
        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandoString);
        requestBody.put("LastName", someRandoString);
        requestBody.put("UserName", someRandoString);
        requestBody.put("password", someRandoString);
        requestBody.put("Emeil", someRandoString + "gmail.com");
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        requestSpecification.body(gson.toJson(requestBody));
        Response response = requestSpecification.post("https://webhook.site/fdee60bb-2241-494b-af02-7e8fa9512f8f");
        int statusCode = response.getStatusCode();
        response.body();
        Assert.assertEquals(statusCode, 200);
    }

    //    добавляем юзера
    @Test
    public void restPut() {
        int empid = 15410;
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        RequestSpecification spec = RestAssured.given();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONObject requestParam = new JSONObject();
        requestParam.put("name", "TestDate");
        requestParam.put("age", 35);
        requestParam.put("salary", 1000);
        spec.body(gson.toJson(requestParam));
        Response response = spec.put("/update/" + empid);
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(requestParam);
    }

    //    я создала класс, чтоб читать определенные поля

    public class deleteUser {
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("status")
        public String status;
        @JsonProperty("message")
        public String message;
        @JsonProperty("data")
        public String data;


    }

//    {"status":"success","data":"15410","message":"Successfully! Record has been deleted"}

    @Test
    public void deleteUser() {
        int empid = 15410;
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        RequestSpecification spec = RestAssured.given();
        Response response = spec.delete("/delete/" + empid);

        String responseString = response.asString();

        JsonObject jsonObjectResponse = new JsonParser().parse(responseString).getAsJsonObject();
        Gson gson = new Gson();
        deleteUser res = gson.fromJson(response.body().asString(),deleteUser.class);
        System.out.println(res.status +" " +res.message +" " +res.data);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

