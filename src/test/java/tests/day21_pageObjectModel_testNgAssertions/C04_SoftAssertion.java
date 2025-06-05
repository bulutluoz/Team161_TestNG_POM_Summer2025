package tests.day21_pageObjectModel_testNgAssertions;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C04_SoftAssertion {
    /*
        softassert'un

        pozitif tarafi
        biz assertAll deyinceye kadar tum assertion'lari calistirmasi

        negatif tarafi
        hatayi assertAll satirinda veriyor
        hata ayiklamak icin ekstra birseyler yapmamiz gerekiyor
     */

    @Test
    public void detayliAramaTesti(){


        // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin

        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(actualUrl.contains(expectedUrlIcerik),"anasayfa url testotomasyonu icermiyor");

        // 2. belirlenen aranacakKelime icin arama yapip,
        //    arama sonucunda urun bulunabildigini test edin

        // aranacakKelime icin arama yapin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        testotomasyonuPage.aramaKutusu.sendKeys(ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin

        String unExpectedSonucYazisi = ConfigReader.getProperty("toUnExpectedSonucYazisi");
        String actualSonucYazisi = testotomasyonuPage.aramaSonucYaziElementi.getText();

        softAssert.assertNotEquals(actualSonucYazisi,unExpectedSonucYazisi,"aranacakKelime aratildiginda urun bulunamadi");

        // 3. ilk urunu tiklayip,
        testotomasyonuPage.bulunanUrunElementleriList
                .get(0)
                .click();

        //    acilan sayfadaki urun isminde
        //    case sensitive olmadan aranacakKelime bulundugunu test edin

        String expectedIsimIcerik = ConfigReader.getProperty("toAranacakKelime");
        String actualIsim = testotomasyonuPage.ilkUrunSayfasindakiIsimElementi
                .getText()
                .toLowerCase();

        softAssert.assertTrue(actualIsim.contains(expectedIsimIcerik),"urun ismi aranacakKelimeyi barindirmiyor");


        // softAssert ile yapilan tum assertion'lari raporlamak icin
        softAssert.assertAll();

        // sayfayi kapatin
        Driver.quitDriver();
    }
}
