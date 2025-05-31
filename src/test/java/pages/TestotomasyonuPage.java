package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TestotomasyonuPage {
    // ARTIK herhangi bir url'deki locate'leri
    // tekrar tekrar yapmayacagiz
    // her url icin bir page class'i olusturup
    // o url deki tum locate'leri Page class'inda kaydedecegiz

    public TestotomasyonuPage(){
        // locate'lerin calisabilmesi icin, WebDriver objesini page class'ina tanitmamiz gerekir
        // POM WebDriver tanitma islemini constructor icinde yapar
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    @FindBy(className = "product-count-text")
    public WebElement aramaSonucYaziElementi;

    @FindBy(className = "prod-img")
    public List<WebElement> bulunanUrunElementleriList;

    @FindBy(xpath = "//*[@class=' heading-sm mb-4']")
    public WebElement ilkUrunSayfasindakiIsimElementi;

    @FindBy(xpath = "(//span[@class='menu-icon-text'])[1]")
    public WebElement accountLinki;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailKutusu;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordKutusu;

    @FindBy(id = "submitlogin")
    public WebElement loginButonu;

    @FindBy(xpath = "//span[.='Logout']")
    public WebElement logoutButonu;

}
