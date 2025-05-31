package tests.day20_pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_PageClassKullanimi {

    @Test
    public void aramaTesti(){
        // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // 2. phone icin arama yapip, arama sonucunda urun bulunabildigini test edin

        // phone icin arama yapin
        WebElement aramaKutusu = driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin
        WebElement sonucYaziElementi = driver.findElement(By.className("product-count-text"));

        String unexpectedSonucYazisi = "0 Products Found";
        String actualSonucYazisi = sonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unexpectedSonucYazisi);

        // 3. ilk urunu tiklayip,
        driver.findElement(By.xpath("(//*[@class='prod-img'])[1]"))
                .click();

        //    acilan sayfadaki urun isminde
        //    case sensitive olmadan phone bulundugunu test edin


        String expectedIsimIcerik = "phone";
        String actualIsim = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"))
                .getText()
                .toLowerCase();

        Assert.assertTrue(actualIsim.contains(expectedIsimIcerik));
    }
}
