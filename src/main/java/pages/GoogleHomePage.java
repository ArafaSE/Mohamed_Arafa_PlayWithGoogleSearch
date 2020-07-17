package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class GoogleHomePage extends PageBase {
    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    // page elements
    @FindBy(name = "q")
    WebElement searchBoxInput;

    @FindBy(css = "ul.erkvQe")
    WebElement searchResultList;

    // variables
    List<WebElement> listResult;

    public void setSearchBoxTxt(String keyword){
        setTextElement(searchBoxInput, keyword);
    }
    public List<WebElement> getSuggestedSearchResult(){
        listResult = searchResultList.findElements(By.className("sbl1"));

        return listResult;
    }

    public void submitSearch(){
        clickEnterKey(searchBoxInput);
    }
}
