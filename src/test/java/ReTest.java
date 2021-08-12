import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReTest {
    User anton;
    @BeforeTest
    public void setUpUser(){
        System.out.println("Set up test anv");
        anton = new User("Anton", 23, true);
    }

    @AfterTest
    public void cleanUp() {
        System.out.println("Clean object");
        anton= null;
    }

    @Test
    public void testSumm(){
        MyFirstTest myFirstTest = new MyFirstTest();
        Assert.assertEquals(myFirstTest.summ(3,3),6,"Summ is wrong");

    }

    @Test
    public void testUserName(){
        Assert.assertEquals(anton.getName(), "Anton", "Wrong Name");
    }

    @Test
    public void testRegistered(){
        Assert.assertTrue(anton.getRegistered(), errorMessage());

    }

    @Test
    public void testAge(){
        Assert.assertEquals(anton.getAge(),23,errorMessage());
    }

    private String errorMessage(){
        return "Something wrong with test data";
    }

}
