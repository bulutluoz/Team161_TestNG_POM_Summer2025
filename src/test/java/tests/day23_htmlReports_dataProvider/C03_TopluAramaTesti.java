package tests.day23_htmlReports_dataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C03_TopluAramaTesti {

    @Test
    public void topluAramaTesti(){
        /*
            Toplu test calistirdigimizda
            failed olan ilk urun icin kod calistirmayi durdurur

            eger tum urunleri test etmesini isterseniz
            try-catch / softAssert olusturup
            tum urunleri aratmasini saglayabiliriz
         */
        // apple, shoe, java, samsung, dress, cokoprens, nutella
        List<String> aranacakUrunlerListesi = new ArrayList<>(Arrays.asList("apple", "shoe", "java", "samsung", "dress", "cokoprens", "nutella" ));

        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // yukarda liste olarak verilen urunlerden
        // herbirini aratip
        // arama sonucunda urun bulunabildigini test edin

        // listedeki herbir urunu aratabilmek ve test yapmak icin loop lazim
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < aranacakUrunlerListesi.size() ; i++) {

            String aranacakKelime = aranacakUrunlerListesi.get(i);
            testotomasyonuPage = new TestotomasyonuPage();
            ReusableMethods.bekle(1);
            testotomasyonuPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);

            String unExpectedAramaSonucu = ConfigReader.getProperty("toUnExpectedSonucYazisi");

            testotomasyonuPage = new TestotomasyonuPage();
            ReusableMethods.bekle(1);
            String actualAramaSonucu = testotomasyonuPage.aramaSonucYaziElementi.getText();

            softAssert.assertNotEquals(actualAramaSonucu,unExpectedAramaSonucu,aranacakKelime +" bulunamadi");

        }

        softAssert.assertAll();

    }
}
