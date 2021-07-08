import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AutomationTask2 {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\ilkin\\OneDrive\\Documents\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

driver.findElement(By.xpath("//input[@id='ctl00_MainContent_username']")).sendKeys("Tester");
driver.findElement(By.xpath("//input[@id='ctl00_MainContent_password']")).sendKeys("test"+ Keys.ENTER);

driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
String amount =""+(int)(0+Math.random()*100);
driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE,amount +Keys.ENTER);
//driver.findElement((By.className("btn_dark"))).click();
String actualValue=driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");
        System.out.println(actualValue);
        String  expectedValue=(Integer.parseInt(amount)>10 ? ""+(int)(Integer.parseInt(amount)*0.92*100): "" +(Integer.parseInt(amount)*100));

        Assert.assertEquals(actualValue,expectedValue);
        driver.findElement((By.className("btn_dark"))).click();

        Faker fake = new Faker();
        String  name =  fake.name().fullName();
        String address = fake.address().streetAddress();
        String city = fake.address().city();
        String state = fake.address().state();
        String zipCode = fake.address().buildingNumber();
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(address);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(city);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(state);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode);
        //String randomCardNo= ""+(int)(Math.random()*2);

        List<String> cardNo = Arrays.asList("ctl00_MainContent_fmwOrder_cardList_0",
                "ctl00_MainContent_fmwOrder_cardList_1", "ctl00_MainContent_fmwOrder_cardList_2");


        String randomCard = cardNo.get(new Random().nextInt(cardNo.size()));
        driver.findElement(By.id(randomCard)).click();
        String visaCard = String.valueOf(4000000000000000L + (long) (Math.random() * 100000000000000L));
        String mastercard = String.valueOf(5000000000000000L + (long) (Math.random() * 100000000000000L));
        String americanExpress = String.valueOf(300000000000000L + (long) (Math.random() * 10000000000000L));
        String cardType = "";
        String cardNumber="";

        switch (randomCard){
            case "ctl00_MainContent_fmwOrder_cardList_0":
                cardNumber=visaCard;
                cardType="Visa";
                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
                break;
            case "ctl00_MainContent_fmwOrder_cardList_1":

                cardNumber = mastercard;
                cardType="MasterCard";
                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
                break;
            case "ctl00_MainContent_fmwOrder_cardList_2":

                cardNumber= americanExpress;
                cardType="American Express";
                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
                break;
        }
String date="11/30";
driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(date);

        driver.findElement(By.xpath("//a[.='Process']")).click();


Assert.assertTrue(driver.getPageSource().contains("New order has been successfully added."));
driver.findElement(By.xpath("//a[.='View all orders']")).click();

        Date dateTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String inputDate= formatter.format(dateTime);
        List<String> actualList = new ArrayList<>();
        List<WebElement> table = driver.findElements(By.tagName("tr"));
        for (WebElement t : table) {
            actualList.add(t.getText());
        }
        List<String> inputList =  Arrays.asList(name, "MyMoney", amount, inputDate, address, city,
                state, zipCode, cardType, cardNumber, date);
        String expected = "";
        for (String input : inputList) {
            expected = expected + input + " " ;
        }
        Assert. assertEquals(actualList.get(2) + " ", expected);

        driver.quit();


        }




//[Glenn Wolf MyMoney 9 07/07/2021 98839 Alverta Row New Colehaven Oregon 844 Visa 4065928716082263 11/30 ]

//[Glenn Wolf MyMoney 9 07/07/2021 98839 Alverta Row New Colehaven Oregon 844 Visa 4065928716082263 11/30 ]
    }






