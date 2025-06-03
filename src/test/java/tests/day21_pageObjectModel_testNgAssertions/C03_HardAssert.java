package tests.day21_pageObjectModel_testNgAssertions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C03_HardAssert {

    @Test
    public void detayliAramaTesti(){

        // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin

        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // 2. belirlenen aranacakKelime icin arama yapip,
        //    arama sonucunda urun bulunabildigini test edin

        // aranacakKelime icin arama yapin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        testotomasyonuPage.aramaKutusu.sendKeys(ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin

        String unExpectedSonucYazisi = ConfigReader.getProperty("toUnExpectedSonucYazisi");
        String actualSonucYazisi = testotomasyonuPage.aramaSonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unExpectedSonucYazisi);

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

        Assert.assertTrue(actualIsim.contains(expectedIsimIcerik));

        // sayfayi kapatin
        Driver.quitDriver();
    }
}
