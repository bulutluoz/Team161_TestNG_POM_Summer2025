package tests.day23_htmlReports_dataProvider;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C05_TopluNegatifLoginTesti {
    @DataProvider
    public static Object[][] kullaniciBilgileriDataProvider() {

        String[][] kullanicilarArrayi = {{"yigit@kmail.com", "125687"},
                                         {"tugba@tmail.com", "345678"},
                                         {"yusuf@mmail.com" ,  "456789"},
                                         {"sumeyra@smail.com", "342321"},
                                         {"canan@cmail.com", "987098"}};
        return kullanicilarArrayi;
    }

    // Verilen listedeki herbir Email-password ikilisi icin
    // asagidaki negatif login testini yapin
    // yigit@kmail.com   125687
    // tugba@tmail.com   345678
    // yusuf@mmail.com   456789
    // sumeyra@smail.com 342321
    // canan@cmail.com 987098

    @Test (dataProvider = "kullaniciBilgileriDataProvider")
    public void negatifLoginTesti(String verilenEmail, String verilenPassword){
        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        ReusableMethods.bekle(1);
        //2- account linkine basin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        ReusableMethods.bekle(1);
        testotomasyonuPage.accountLinki.click();

        //3- verilen email, verilen password ile giris yapmayi deneyin

        testotomasyonuPage.emailKutusu.sendKeys(verilenEmail);
        testotomasyonuPage.passwordKutusu.sendKeys(verilenPassword);

        //4- Login butonuna basarak login olmayi deneyin
        testotomasyonuPage.loginButonu.click();

        ReusableMethods.bekle(1);
        //5- Basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(testotomasyonuPage.emailKutusu.isDisplayed());

        //6- sayfayi kapatin
        Driver.quitDriver();
    }
}
