import org.testng.Assert;
import org.testng.annotations.Test;

public class Retest {

    @Test
    public void testSumm(){
        MyFirstTest myFirstTest = new MyFirstTest();
        Assert.assertEquals(myFirstTest.summ(2,3),4,"Summ is wrong");

    }
}
