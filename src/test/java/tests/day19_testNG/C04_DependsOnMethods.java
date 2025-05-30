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

public class C04_DependsOnMethods {
    /*
        Siralamadan bagimsiz olarak
        eger birbirine bagli testler varsa
        yani A testi calisip passed olmazsa B testinin calismasi anlamsiz ise

        1- B testinin onune dependsOnMethods A yazarsaniz
           A testi  calisip passed olmazsa B hic calismaz

        2- her ne kadar dependsOnMethods ozelligi siralama icin kullanilmasa da
           sira B method'una geldiginde A'nin calisip passed olmasini kontrol eder
           eger A calismadiysa calismasini saglar

        3- normalde biz herbir testi bagimsiz olarak calistirabiliriz
           Ancak B testini tek basina calistirmak icin run ederseniz
           once A'nin calismasini saglar, A calisip passed olursa
           B de calisir

           Ama bu ozellik sadece 2 method icin gecerlidir
           birbirine bagimli 3 test varsa
           3.testi calistirmak istedigimizde
           No tests were found (calistirilabilecek bir test bulunamadi) der
           ve hic bir testi calistirmaz

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

    @Test   // priority = 0
    public void anasayfaTesti(){
        // 1. Testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));
    }

    @Test (dependsOnMethods = "anasayfaTesti") // priority = 0
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

    @Test (dependsOnMethods = "phoneAramaTesti") //(priority = 20)
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
