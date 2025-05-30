package tests.day19_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class C05_DriverClassKullanimi {
    /*
        TestNG Page Object Model kullanir

        Temel hedefimiz
        Test class'i icerisinde dinamik olmayan hicbir data kalmamasi
        (degeri degistiginde duzeltmek icin test class'ina gelme mecburiyeti olmayan)
     */

    @Test
    public void aramaTesti() throws InterruptedException {

        Driver.getDriver().get("https://www.testotomasyonu.com");
        Thread.sleep(2000);

        WebElement aramaKutusu = Driver.getDriver().findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);
        Thread.sleep(2000);

        WebElement sonucYaziElementi = Driver.getDriver().findElement(By.className("product-count-text"));

        String unexpectedSonucYazisi = "0 Products Found";
        String actualSonucYazisi = sonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unexpectedSonucYazisi);


        Driver.quitDriver();

    }
}
