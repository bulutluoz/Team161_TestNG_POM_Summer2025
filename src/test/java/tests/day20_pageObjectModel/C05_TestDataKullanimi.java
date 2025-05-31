package tests.day20_pageObjectModel;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C05_TestDataKullanimi {

    @Test
    public void pozitifLogintesti(){
        /*
            Page Object Model'in temel amaci
            Test Method'larini dinamik yapmak
            yani locate, browser veya test datasi degistiginde
            icinde oldugumuz C05 class'ina gelmeye zorunlu olmamamizdir

            locate'leri Page class'i ile hallettik

            test datalarini da configuration.properties ile cozmek istiyoruz

            java ile bir dosyaya ulasip oradaki bilgileri almak icin
            dosyaYolu, FileInputStream ve loop'lara ihtiyac var
            ama herseferinde bu islemleri yapmak zahmetli olacagindan

            POM'de configuration.properties dosyasina gidip
            bizim verdigimiz key'e ait value'yu bize getirecek
            bir MEYDANCI kullaniyoruz

         */


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
