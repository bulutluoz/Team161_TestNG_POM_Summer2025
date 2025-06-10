package tests.day23_htmlReports_dataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C04_DataProviderIleTopluAramaTesti {
    @DataProvider
    public static Object[][] kelimeDataProvider() {

        String[][] aranacakKelimelerArrayi = {{"apple"}, {"shoe"}, {"java"}, {"samsung"}, {"dress"}, {"cokoprens"}, {"nutella"}};

        return aranacakKelimelerArrayi;
    }

    // "apple", "shoe", "java", "samsung", "dress", "cokoprens", "nutella"
    /*
        data provider ile test yapmak icin
        1- ONCE sanki sadece tek bir urun aratilacakmis gibi test method'unu yaziyoruz
        2- aratilacak urunu de parametre olarak yaziyoruz
        3- aratilacak kelimeleri parametre olarak yollamasi icin
           bir dataProvider olusturun
        4- olusturulan data provider'i testMethod'una tanitin

        Data provider "2 katli Object bir array" donduren
        bir method'dur
     */


    @Test(dataProvider = "kelimeDataProvider")
    public void test01(String aranacakKelime){
        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // yukarda liste olarak verilen urunlerden
        // herbirini aratip

        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();

        testotomasyonuPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin
        String unExpectedAramaSonucu = ConfigReader.getProperty("toUnExpectedSonucYazisi");
        String actualAramaSonucu = testotomasyonuPage.aramaSonucYaziElementi.getText();

        Assert.assertNotEquals(actualAramaSonucu,unExpectedAramaSonucu);

    }
}
