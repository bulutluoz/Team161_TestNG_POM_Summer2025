package tests.day19_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_BeforeAfter {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        // gerekli ayarlari yapin
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown(){
        // sayfayi kapatin
        driver.quit();
    }

    @Test
    public void aramaTesti(){

        // testotomasyonu anasayfaya gidin
        driver.get("https://www.testotomasyonu.com");

        // phone icin arama yapin
        WebElement aramaKutusu = driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin
        WebElement sonucYaziElementi = driver.findElement(By.className("product-count-text"));

        String unexpectedSonucYazisi = "0 Products Found";
        String actualSonucYazisi = sonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unexpectedSonucYazisi);

    }
}
