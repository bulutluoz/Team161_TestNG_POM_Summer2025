package tests.day19_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C03_Priority {
    /*
        TestNG standart olarak isim siralamasina gore testleri calistirir

        EGER farkli bir siralama isterseniz
        priority ile siralamayi kendiniz belirleyebilirsiniz

        Kurallar :
        1- priority degeri kucuk olandan buyuge dogru calisir
        2- eger bir test method'una priority degeri atanmazsa
           TestNG default olarak o method'a priority degeri olarak 0 verir
        3- eger birden fazla method'a ayni priority degeri atanirsa
           priority deger ayni olanlar
           kendi iclerinde isim sirasina gore calisir

     */

    // 3 farkli test method'u olusturarak, asagidaki gorevleri yapin
    // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin
    // 2. phone icin arama yapip, arama sonucunda urun bulunabildigini test edin
    // 3. ilk urunu tiklayip, acilan sayfadaki urun isminde
    //    case sensitive olmadan phone bulundugunu test edin
    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

    @Test // priority = 0
    public void anasayfaTesti(){
        // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));
    }

    @Test // priority = 0
    public void phoneAramaTesti(){
        // 2. phone icin arama yapip, arama sonucunda urun bulunabildigini test edin

        // phone icin arama yapin
        WebElement aramaKutusu = driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin
        WebElement sonucYaziElementi = driver.findElement(By.className("product-count-text"));

        String unexpectedSonucYazisi = "0 Products Found";
        String actualSonucYazisi = sonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unexpectedSonucYazisi);
    }

    @Test(priority = 20)
    public void ilkUrunIsimTesti(){
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
