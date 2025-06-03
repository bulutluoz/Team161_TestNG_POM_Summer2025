package tests.day21_pageObjectModel;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C01_TestDataKullanimi {
    /*
        POM'in temel hedefi
        locate, test datasi veya test calistirma tercihlerinde
        herhangi bir degisiklik olursa
        Test method'una gelmeden
        belirlenmis yerlerdeki duzeltmeleri 1 kere yapip
        TUM test method'larinin update edilmesini saglamaktir
     */

    @Test
    public void pozitifLogintesti(){
        // 1- https://www.testotomasyonu.com/ anasayfasina gidin
        // Driver.getDriver().get("https://www.testotomasyonu.com/"); // Dinamik degil
        Driver.getDriver().get(ConfigReader.getProperty("toUrl")); // Dinamik

        // 2- account linkine basin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        ReusableMethods.tumSayfaResimCekTarihli(Driver.getDriver());
        ReusableMethods.bekle(2);
        testotomasyonuPage.accountLinki.click(); // Dinamik

        // 3- Kullanici email'i olarak gecerli email girin
        // testotomasyonuPage.emailKutusu.sendKeys("wise@gmail.com"); // Dinamik degil
        testotomasyonuPage.emailKutusu.sendKeys(ConfigReader.getProperty("toGecerliEmail")); // Dinamik

        // 4- Kullanici sifresi olarak gecerli password girin
        // testotomasyonuPage.passwordKutusu.sendKeys("123456"); // Dinamik degil
        testotomasyonuPage.passwordKutusu.sendKeys(ConfigReader.getProperty("toGecerliPassword")); // Dinamik

        // 5- Login butonuna basarak login olun
        testotomasyonuPage.loginButonu.click(); // Dinamik

        // 6- Basarili olarak giris yapilabildigini test edin

        Assert.assertTrue(testotomasyonuPage.logoutButonu.isDisplayed()); // Dinamik

        // 7- logout olun
        testotomasyonuPage.logoutButonu.click(); // Dinamik

        // 8- sayfayi kapatin
        Driver.quitDriver();


    }
}
