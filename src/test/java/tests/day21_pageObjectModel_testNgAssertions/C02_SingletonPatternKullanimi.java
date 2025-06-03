package tests.day21_pageObjectModel_testNgAssertions;

import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ReusableMethods;

public class C02_SingletonPatternKullanimi {

    @Test
    public void test01(){
        // POM dizayn edenler
        // Driver class'ininin farkli yontemlerle kullanilmasini engellemek icin
        // Singleton pattern'i tercih etmislerdir

        // Driver driverObjesi = new Driver();


        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get("https://www.testotomasyonu.com");

        ReusableMethods.bekle(2);

        // sayfayi kapatin
        Driver.quitDriver();
    }
}
