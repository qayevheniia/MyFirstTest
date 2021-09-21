package IntegrationTest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;

public class IntTest {

    @Test
    public  void myRestTest(){
        RestAssured.when()
                .get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")
                .then().assertThat().statusCode(200)
                .and().body("args.foo1", is("bar1"))
                .and().body("args.foo2", is("bar2"));
    }

    @Test
    public void JsonPost(){
        String someRandoString = String.format("%1$TH%1$TM%1$TS", new Date());
        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandoString);
        requestBody.put("LastName", someRandoString);
        requestBody.put("UserName", someRandoString);
        requestBody.put("password", someRandoString);
        requestBody.put("Emeil", someRandoString +"gmail.com");
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(requestBody.toString());
        Response response = requestSpecification.post("https://webhook.site/fdee60bb-2241-494b-af02-7e8fa9512f8f");
        int statusCode = response.getStatusCode();
        response.body();
        Assert.assertEquals(statusCode, 200);
    }

//    добавляем юзера
    @Test
    public void restPut(){
        int empid = 15410;
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        RequestSpecification spec = RestAssured.given();
        JSONObject requestParam = new JSONObject();
        requestParam.put("name", "TestDate");
        requestParam.put("age", 35);
        requestParam.put("salary", 1000);
        spec.body(requestParam.toString());
        Response response = spec.put("/update/" +empid);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

//    удаляем юзера
    @Test
    public void deleteUser(){
            int empid = 15410;
            RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
            RequestSpecification spec = RestAssured.given();
            Response response = spec.delete("/delete/" +empid);
            String responseString = response.asString();
        System.out.println(responseString);
        Assert.assertEquals(response.getStatusCode(), 200);
    } 
}
