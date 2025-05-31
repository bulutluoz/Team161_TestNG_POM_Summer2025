package tests.day20_pageObjectModel;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class C01_BasicDriverClassKullanimi {

    @Test
    public void testotomasyonuTesti(){

        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get("https://www.testotomasyonu.com");

        // url'in testotomasyonu icerdigini test edin
        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // sayfayi kapatin
        Driver.quitDriver();

    }

    @Test
    public void wisequarterTesti(){

        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get("https://www.wisequarter.com");

        // url'in wisequarter icerdigini test edin
        String expectedUrlIcerik = "wisequarter";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // sayfayi kapatin
        Driver.quitDriver();

    }

    @Test
    public void youtubeTesti(){

        // youtube anasayfaya gidin
        Driver.getDriver().get("https://www.youtube.com");

        // url'in youtube icerdigini test edin
        String expectedUrlIcerik = "youtube";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // sayfayi kapatin
        Driver.quitDriver();

    }

}
