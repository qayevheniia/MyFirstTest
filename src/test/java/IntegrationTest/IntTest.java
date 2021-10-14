package IntegrationTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;


import static org.hamcrest.Matchers.is;

public class IntTest {



    @DataProvider (name="links")
    public Object[] [] dp(){
        return  new Object[][] {{"http://google.com"}, {"http://google.ua"}};

    }





public Properties initProps() throws IOException {
    String env = System.getProperty("env");
        FileReader reader = new FileReader(String.format("src/main/resources/%s.properties", env));

    Properties p =new Properties();
    p.load(reader);
    return p;

}

    @Test(dataProvider = "links")

    public  void uiTest(String link) throws IOException {
        Selenide.open(link);
        System.out.println(initProps().getProperty("env"));
        String login = initProps().getProperty("login");
        String password = initProps().getProperty("password");
        System.out.println(login +password);

    }

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

    @Test
    public void  uiSelenide(){
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Selenide.open("https://google.com");
        Selenide.open("https://google.com.ua");

    }

    @Test
//    Условие задачи:
//   1. Создайте массив из 8 случайных целых чисел из отрезка [1;10]
//   2. Выведите массив на экран в строку
//3. Замените каждый элемент с нечётным индексом на ноль
//4. Снова выведете массив на экран на отдельной строке
    public void task(){
        int [] newMas = new int [8];
        for (int i=0; i<
                newMas.length; i++){
            newMas[i]= (int)(Math.random()*11);}
            System.out.println(Arrays.toString(newMas));
        for (int newMas2: newMas
             ) {if (newMas2%2==1){
                 newMas2=0;
        }
            System.out.print(newMas2);

        }
    }

//    "Что это такое: синий, большой, с усами и полностью набит зайцами?" Ответ: троллейбус.
//    Можно возражать, конечно, какого цвета сейчас троллейбусы - но мы не об этом.
//Представим, что Вы загадываете эту загадку пользователю. Создайте программу, которая будет считывать с консоли ответ:
//у пользователя есть 3 попытки. После трех ответов программа должна завершиться;
//если пользователь вводит "Троллейбус", мы выводим в консоль "Правильно!" и выходим из цикла;
//если пользователь вводит "Сдаюсь", мы выводим в консоль "Правильный ответ: троллейбус." и выходим из цикла;
//если пользователь вводит любой другой ответ, мы выводим в консоль "Подумай еще." и продолжаем цикл.
@Test
        public static void main(String [] args) {
            System.out.println("Что это такое: синий, большой, с усами и полностью набит зайцами?");
            int a =0;
            boolean exit = false;

            while ((a < 2) && (exit==false)) {
                Scanner ln = new Scanner(System.in);
                String responds = ln.next ();
                switch (responds) {
                    case "Троллейбус":
                        System.out.println("Правильно!");
                        exit = true;
                        break;

                    case "Сдаюсь":
                        System.out.println("Правильный ответ: троллейбус.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Подумай еще.");
                }
                a++;
            }
        }

    }


