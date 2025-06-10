package tests.day23_htmlReports_dataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C03_TopluAramaTesti {

    @Test
    public void topluAramaTesti(){
        // apple, shoe, java, samsung, dress, cokoprens, nutella
        List<String> aranacakUrunlerListesi = new ArrayList<>(Arrays.asList("apple", "shoe", "java", "samsung", "dress", "cokoprens", "nutella" ));

        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // yukarda liste olarak verilen urunlerden
        // herbirini aratip
        // arama sonucunda urun bulunabildigini test edin

        // listedeki herbir urunu aratabilmek ve test yapmak icin loop lazim
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();

        for (int i = 0; i < aranacakUrunlerListesi.size() ; i++) {

            String aranacakKelime = aranacakUrunlerListesi.get(i);
            testotomasyonuPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);

            String unExpectedAramaSonucu = ConfigReader.getProperty("toUnExpectedSonucYazisi");
            String actualAramaSonucu = testotomasyonuPage.aramaSonucYaziElementi.getText();

            Assert.assertNotEquals(actualAramaSonucu,unExpectedAramaSonucu);

        }

    }
}
