package tests.day21_pageObjectModel_testNgAssertions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ZeroWebappPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C05_SoftAssertion {

    @Test
    public void test01(){
        // 1. “http://zero.webappsecurity.com/” Adresine gidin
        Driver.getDriver().get(ConfigReader.getProperty("zeroUrl"));

        // 2. webbappsecurity ana sayafaya gittiginizi dogrulayin (verify)
        SoftAssert softAssert = new SoftAssert();

        String expectedUrl = ConfigReader.getProperty("zeroUrl");
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl,expectedUrl,"url expected url'den farkli");

        // 3. Sign in butonuna basin
        ZeroWebappPage zeroWebappPage = new ZeroWebappPage();
        zeroWebappPage.ilkSayfaSignInLinki.click();

        // 4. Login kutusuna “username” yazin
        zeroWebappPage.loginKutusu.sendKeys(ConfigReader.getProperty("zeroUsername"));

        // 5. Password kutusuna “password” yazin
        zeroWebappPage.passwordKutusu.sendKeys(ConfigReader.getProperty("zeroPassword"));

        // 6. Sign in tusuna basin
        zeroWebappPage.loginSayfasiSignInButonu.click();

        // 7. Back tusuna basin
        Driver.getDriver().navigate().back();

        // 8. Giris yapilabildigini dogrulayin
        softAssert.assertTrue(zeroWebappPage.settingsLinki.isDisplayed(),"sisteme giris yapilamadi");

        // 9. Online banking menusunu tiklayin
        zeroWebappPage.onlineBankingMenu.click();

        //10. Pay Bills sayfasina gidin
        zeroWebappPage.payBillsLinki.click();

        //11. “Purchase Foreign Currency” tusuna basin
        zeroWebappPage.purchaseFCLinki.click();

        //12. Currency dropdown menusunun erisilebilir oldugunu dogrulayin
        //13. “Currency” dropdown menusunden Eurozone’u secin
        //14. "Eurozone (euro)" secildigini dogrulayin
        //15. Dropdown menude 16 option bulundugunu dogrulayin.
        //16. Dropdown menude "Canada (dollar)" bulunduğunu dogrulayin

        softAssert.assertAll();
        //17. Sayfayi kapatin
        Driver.quitDriver();
    }
}
