import dev.failsafe.internal.util.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TutorialsNinjaTest extends BaseTest {


    @Test
    public void TestSortAlphabeticallyAscending()
    {
        TutorialsNinjaPage tnPage = new TutorialsNinjaPage(driver);
        tnPage.goToURL();
        Assert.isTrue(tnPage.verifyCurrencyDropDownExists(),"currency drop down not exists in the page !");
        tnPage.searchForIpod("iPod");
        Boolean isProductListSorted = tnPage.isProductsListSorted();
		Assert.isTrue(isProductListSorted,"product list is not sorted !.");
        Assert.isTrue(isProductListSorted,"list is not sorted , user 2");
    }

    @Test
    public void ipodStorage() {

        TutorialsNinjaPage tnPage = new TutorialsNinjaPage(driver);
        tnPage.goToURL();
        Assert.isTrue(tnPage.verifyCurrencyDropDownExists(),"currency drop down not exists in the page !");
        tnPage.searchForIpod("iPod");

        // find the min and max IPODs according to price
        List<IPod> ipods = tnPage.getIPodList();
        int min = ipods.stream().mapToInt(IPod::getPrice).min().orElse(Integer.MAX_VALUE);
        int max = ipods.stream().mapToInt(IPod::getPrice).max().orElse(Integer.MIN_VALUE);
    }

}
