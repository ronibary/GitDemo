import com.google.common.collect.Comparators;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/*


https://tutorialsninja.com/demo/


 */


public class TutorialsNinjaPage extends BasePage {

    private String HOME_URL = "https://tutorialsninja.com/demo/";

    //locators

    private By currencyDropDown = By.xpath("//span[contains(.,'Currency')]");
    private By searchBox = By.cssSelector("input[placeholder='Search']");
    private By searchButton = By.cssSelector(".fa.fa-search");
    private By listViewButton = By.cssSelector(".fa.fa-th-list");
    private By sortSelectControl = By.cssSelector("#input-sort");

    private By productList = By.cssSelector(".product-list");
    private By productName = By.cssSelector(".product-list div div h4 a");
    private By imgProductURL = By.cssSelector(".product-list div a img");
    private By prodDescription = By.cssSelector(".product-list div div p:first-of-type");
    private By prodPrice = By.xpath("//p[@class='price']");









    public TutorialsNinjaPage(WebDriver driver) {
        super(driver);
    }

    public void goToURL()
    {
        driver.get(HOME_URL);
    }

    public Boolean verifyCurrencyDropDownExists()
    {
        WebElement currencyElement = null;
        try
        {
            currencyElement = findElement(currencyDropDown);
        }
        catch(Exception err)
        {
            System.out.println("currency drop down not found in page: "+  err.getMessage());
        }

        return currencyElement != null;
    }



    public void searchForIpod(String searchText)
    {
        findElement(searchBox).sendKeys(searchText);
        findElement(searchButton).click();
        waitForElelementToBeClickable(listViewButton).click();

        // sort
        WebElement selectElement = findElement(sortSelectControl);
        Select sortDropDown = new Select(selectElement);
        sortDropDown.selectByVisibleText("Name (A - Z)");
    }

    public List<WebElement> getProductsList()
    {
        List<WebElement> products = driver.findElements(productList);
        return products;
    }

    public Boolean isProductsListSorted()
    {
        List<String> productNameList = new ArrayList<>();

        List<WebElement> products = getProductsList();
        for(WebElement product : products )
        {
            WebElement prodName = product.findElement(productName);
            productNameList.add(prodName.getText());
        }

        boolean sorted = Ordering.natural().isOrdered(productNameList);
        return sorted;
    }

    public List<IPod> getIPodList()
    {
        List<IPod> productList = new ArrayList<>();

        List<WebElement> products = getProductsList();
        for(WebElement product : products )
        {
            IPod ipod = new IPod();
            WebElement prodName = product.findElement(productName);

            String ipodName = prodName.getText().substring(5);
            ipod.setIPodName(ipodName);

            String imgURL = product.findElement(imgProductURL).getAttribute("src");
            ipod.setPictureURL(imgURL);

            String desc = product.findElement(prodDescription).getText();

            ipod.setDescription(desc);

            String priceText = product.findElement(prodPrice).getText();

            String[] priceWordsArray = priceText.split("\n");
            String price = priceWordsArray[0].substring(1);
			
			ipod.setPrice((int) Float.parseFloat(price));

            productList.add(ipod);
        }

        return productList;
    }



}
