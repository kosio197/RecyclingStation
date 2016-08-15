package bg.softuni.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

public class ZeroTest1 {
    @Test
    public void test() throws Exception {
        String str = "ProcessGarbage Glass|10|1.14|Recyclable\n" + "ProcessGarbage Wood|33.156|3.657|Burnable\n"
                + "ProcessGarbage UsedShampooBottles|2.25|1.57|Storable\n" + "Status\n"
                + "ProcessGarbage OldTires|13.88|5.99|Storable\n" + "Status\n" + "TimeToRecycle";

        InputStream is = new ByteArrayInputStream(str.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);

        Main.solve(is, printStream);

        String expected = "10.00 kg of Glass successfully processed!\n" + "33.16 kg of Wood successfully processed!\n"
                + "2.25 kg of UsedShampooBottles successfully processed!\n" + "Energy: 90.84 Capital: 3997.70\n"
                + "13.88 kg of OldTires successfully processed!\n" + "Energy: 80.03 Capital: 3943.66";

        String actual = baos.toString();

        Assert.assertEquals(expected, actual);
    }
}
