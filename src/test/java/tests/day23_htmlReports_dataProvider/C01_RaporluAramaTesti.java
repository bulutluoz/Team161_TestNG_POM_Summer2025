package tests.day23_htmlReports_dataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C01_RaporluAramaTesti extends TestBaseRapor {
    /*
        TestNG kendisi HTML raporlar olusturmaz
        ama aven stack / extent reports dependency sayesinde
        VAR OLAN testNG test method'larini
        HTML rapor olusturacak hale donusturebiliriz

        1- oncelikle TestNG ile test method'unu olusturun
        2- TestBaseRapor'u kullanabilmek icin TestBaseRapor'a child olalim
        3- Html raporlar icin 3 objeye ihtiyacimiz var
           iki tanesini TestBaseRapor'da olusturup deger atamasi ve
           ilgili ayarlar yapildi
           Ucuncu obje olan extentTest objesine
           her Test Method'unda yeniden deger atamasi yapmak gereklidir
        4- Html raporda gorunmesini istediginiz
           her aciklama icin extentTest ile info yazin
     */

    @Test
    public void detayliAramaTesti(){
        extentTest = extentReports.createTest("Detayli arama testi",
                "Kullanici belirlenen aranacakKelime'yi aratabilmeli");

        // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin

        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanici Testotomasyonu anasayfaya gider");

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));
        extentTest.pass("url'in testotomasyonu icerdigini test eder");

        // 2. belirlenen aranacakKelime icin arama yapip,
        //    arama sonucunda urun bulunabildigini test edin

        // aranacakKelime icin arama yapin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        testotomasyonuPage.aramaKutusu.sendKeys(ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);
        extentTest.info("belirlenen aranacakKelime icin arama yapar");
        // arama sonucunda urun bulunabildigini test edin

        String unExpectedSonucYazisi = ConfigReader.getProperty("toUnExpectedSonucYazisi");
        String actualSonucYazisi = testotomasyonuPage.aramaSonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unExpectedSonucYazisi);
        extentTest.pass("arama sonucunda urun bulunabildigini test eder");

        // 3. ilk urunu tiklayip,
        testotomasyonuPage.bulunanUrunElementleriList
                            .get(0)
                            .click();
        extentTest.info("ilk urunu tiklar");
        //    acilan sayfadaki urun isminde
        //    case sensitive olmadan aranacakKelime bulundugunu test edin

        String expectedIsimIcerik = ConfigReader.getProperty("toAranacakKelime")+111;
        String actualIsim = testotomasyonuPage.ilkUrunSayfasindakiIsimElementi
                                                .getText()
                                                .toLowerCase();

        Assert.assertTrue(actualIsim.contains(expectedIsimIcerik));
        extentTest.pass("urun isminde case sensitive olmadan aranacakKelime bulundugunu test eder");
        // sayfayi kapatin

        extentTest.info("sayfayi kapatir");
    }
}
