package tests.day20_pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.Driver;

import java.util.List;

public class C02_PageClassKullanimi {

    @Test
    public void aramaTesti(){
        // 1. Testotomasyonu anasayfaya gidip,
        // url'in testotomasyonu icerdigini test edin

        Driver.getDriver().get("https://www.testotomasyonu.com");

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // 2. phone icin arama yapip, arama sonucunda urun bulunabildigini test edin

        // phone icin arama yapin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        testotomasyonuPage.aramaKutusu.sendKeys("phone" + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin

        String unexpectedSonucYazisi = "0 Products Found";
        String actualSonucYazisi = testotomasyonuPage.aramaSonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unexpectedSonucYazisi);

        // 3. arama sonucunda 4 urun bulunabildigini test edin

        int expectedUrunSayisi = 4;
        int actualUrunSayisi = testotomasyonuPage.bulunanUrunElementleriList
                                                    .size();
        Assert.assertEquals(actualUrunSayisi,expectedUrunSayisi);



        // 4. ilk urunu tiklayip,
        testotomasyonuPage.bulunanUrunElementleriList
                            .get(0)
                            .click();

        //    acilan sayfadaki urun isminde
        //    case sensitive olmadan phone bulundugunu test edin


        String expectedIsimIcerik = "phone";
        String actualIsim = testotomasyonuPage.ilkUrunSayfasindakiIsimElementi
                                                .getText()
                                                .toLowerCase();

        Assert.assertTrue(actualIsim.contains(expectedIsimIcerik));

        // sayfayi kapatin
        Driver.quitDriver();
    }
}
