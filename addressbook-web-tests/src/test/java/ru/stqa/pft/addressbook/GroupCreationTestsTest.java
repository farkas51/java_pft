package ru.stqa.pft.addressbook;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class GroupCreationTestsTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeMethod
  public void setUp() {
    System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void groupCreationTests() {
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(1536, 824));
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    driver.findElement(By.linkText("groups")).click();
    driver.findElement(By.name("new")).click();
    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).sendKeys("test1");
    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).sendKeys("test2");
    driver.findElement(By.name("group_footer")).click();
    driver.findElement(By.name("group_footer")).sendKeys("test3");
    driver.findElement(By.name("submit")).click();
  }
}
