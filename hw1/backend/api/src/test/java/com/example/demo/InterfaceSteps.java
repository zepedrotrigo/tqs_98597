package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.hamcrest.MatcherAssert.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.text.IsEmptyString;
import java.util.concurrent.TimeUnit;


public class InterfaceSteps {
    private WebDriver driver;

    // Scenario: Search for world data in a given day
    @When("I load the webpage {string}")
    public void loadWebPage(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @And("I choose a date on the calendar")
    public void checkCovidDataOnListBar() {
        driver.findElements(By.tagName("button")).get(0).click();
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div[1]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button")).click();
    }

    @And("I click the search button")
    public void clickHomeSearchButton() {
        driver.findElement(By.id("search-btn")).click();
    }

    @Then("data field with id {string} is loaded from the API")
    public void checkIfDataisLoaded(String id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        
        assertThat(driver.findElement(By.id(id)).getText(),
                CoreMatchers.not(IsEmptyString.emptyString()));
    }


    // Scenario: Search for Regional data for a given Country and a given day
    
    @And("I click tab with id {string} in the sidebar")
    public void NavigateToRegionalDataPage(String id) {
        driver.findElement(By.id(id)).click();
    }

    @And("I choose {string} from the select element")
    public void SelectCountry(String country) throws InterruptedException {
        driver.findElement(By.id("country-select")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.cssSelector("li[data-value='Poland']")).click();
    }
    
    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
