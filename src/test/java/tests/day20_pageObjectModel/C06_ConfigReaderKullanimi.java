package tests.day20_pageObjectModel;

import org.testng.annotations.Test;
import utilities.ConfigReader;

public class C06_ConfigReaderKullanimi {

    @Test
    public void test01(){

        System.out.println(ConfigReader.getProperty("toUrl")); // https://www.testotomasyonu.com

        System.out.println(ConfigReader.getProperty("toGecerliEmail")); // wise@gmail.com

        System.out.println(ConfigReader.getProperty("toGecersizPassword")); // 654321

        System.out.println(ConfigReader.getProperty("buAksamNeYiyicez?")); // null
    }
}
