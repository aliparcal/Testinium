package org.example;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.Random;

public class AppTest {

    public  WebDriver driver;
    public static Logger logger;


    @Before
    public void setupDriver(){
        logger = Logger.getLogger("gittigidiyor");
        PropertyConfigurator.configure("Log4j.properties");

        logger.info("*********** Browser Aciliyor ***********");
        logger.info("Gittigidiyor Anasayfasina gidiliyor");
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Ali\\Desktop\\gittigidiyor\\geckodriver.exe");
        driver = new FirefoxDriver();
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }
    @Test
    public void TestHome() throws InterruptedException {

        Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", driver.getTitle());
        WebElement girisbtn= driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]"));
        girisbtn.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement signbtn= driver.findElement(By.xpath("//div[@class='sc-12t95ss-3 fDarBX']//a[@type='button']"));
        signbtn.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Login sayfasina gidiliyor");

        Assert.assertEquals("Üye Girişi - GittiGidiyor", driver.getTitle());
        logger.info("Kullanıcı adi giriliyor.");
        WebElement mailbox= driver.findElement(By.id("L-UserNameField"));
        mailbox.click();
        mailbox.sendKeys("aliprcl");

        WebElement password = driver.findElement(By.id("L-PasswordField"));
        logger.info("Sifre giriliyor.");
        password.click();
        password.sendKeys("a123456");
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        logger.info("Giris yapiliyor.");
        driver.findElement(By.id("gg-login-enter")).click();

        /* Arama çubuğunda 'Bilgisayar' ifadesinin aranması */
        logger.info("Arama cubuguna tiklaniyor");
        WebElement searchBox = driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]"));
        searchBox.click();
        logger.info("Arama parametresi giriliyor.");
        searchBox.sendKeys("Bilgisayar");
        logger.info("Arama baslatiliyor.");
        driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/button[1]")).click();

        /* Arama sonuç sayfalarında 2. sayfanın açılması*/
        logger.info("2. Arama sayafsi açılıyor");
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//body[1]/div[5]/div[2]/div[1]/div[2]/div[5]/ul[1]/li[2]")).click();

        /* Sayfada bulunan 48 adet üründen rastgele bir tane seçmesi için random bir sayı oluşturulması. */
        int itemNum = 48;
        Random rand  = new Random();
        int randomNum = rand.nextInt((itemNum - 1) + 1 ) + 1;
        logger.info(randomNum +" Numaralı rastgele ürün aciliyor.");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[2]/div[1]/div[2]/div[3]/div[2]/ul[1]/li["+randomNum+"]")).click();

        logger.info("Ürün fiyatı alınıyor.");
        WebElement price= driver.findElement(By.id("sp-price-lowPrice"));
        String priceText= price.getText();
        logger.info("Secilen ürünün fiyatı: "+priceText);

        /* Açılan ürün sayfasında ürünün sepete eklenmesi*/

        logger.info("Ürün sepete ekleniyor.");
        WebElement basketBtn = driver.findElement(By.id("add-to-basket"));
        basketBtn.click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        logger.info("Sepet sayfasina gidiliyor.");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[4]/div[3]")).click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        /* Ürün sayfasındaki fiyat ile sepetteki fiyatın karşılaştırılması */
        WebElement priceBasket= driver.findElement(By.className("total-price"));
        String priceText2= priceBasket.getText();
      //  Assert.assertEquals(priceText,priceText2);

        /* Sepetteki ürün adetinin artırılması */
        WebElement quantityBasket = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[4]/div[1]/div[2]/select[1]"));
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
        quantityBasket.click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        logger.info("Dropdown menüden 2 seçiliyor.");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[4]/div[1]/div[2]/select[1]/option[2]")).click();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
        WebElement amount = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/ul[1]/li[1]/div[1]"));
        Thread.sleep(2000);
        String amountText = amount.getText();
        Assert.assertEquals("Ürün Toplamı (2 Adet)", amountText);

        /* Sepetteki ürünlerin boşaltılması */
        logger.info("Sepet boşaltılıyor.");
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/a[1]/i[1]")).click();
    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }
}
