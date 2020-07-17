package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GoogleResultsPage extends PageBase{
    public GoogleResultsPage(WebDriver driver) {
        super(driver);
    }

    // page elements
    @FindBy(xpath = "//*[@id=\"rso\"]/div[1]/div/div[1]/a/h3")
    WebElement firstResultTxt;

    public String getTitleOfTheFirstResult(){
        return firstResultTxt.getText();
    }
}
