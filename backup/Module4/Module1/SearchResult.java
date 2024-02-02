package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        WebElement titleofelement = parentElement.findElement(
                By.xpath(".//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));

        // im using .//p, because i have to get the title of the result from the parent element

        titleOfSearchResult = titleofelement.getText();

        return titleOfSearchResult;

    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizechart =
                    parentElement.findElement(By.xpath(".//button[text()='Size chart']"));
            sizechart.click();


            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */

            WebElement sizechart =
                    parentElement.findElement(By.xpath(".//button[text()='Size chart']"));

            if (sizechart.isDisplayed()) {

                String text = sizechart.getText();

                if (text.toUpperCase().equals("SIZE CHART")) {
                    status = true;
                }
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders,
            List<List<String>> expectedTableBody, WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table header in
             * the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body in the
             * same order
             */
            //List<String> expectedTableHeaders = Arrays.asList("Size", "UK/INDIA", "EU", "HEEL TO TOE"); 

            //we need to compare our actual value with the expected value which was already declared

            Thread.sleep(3000);
            for (int i = 0; i < expectedTableHeaders.size(); i++) {
                String expected = expectedTableHeaders.get(i);
                int row = i + 1;
                WebElement tableheadelement =
                        driver.findElement(By.xpath("//table/thead/tr/th[" + row + "]"));
                String actual = tableheadelement.getText();
                if (!expected.equals(actual)) {
                    status = false;

                }
            }

            // // List<List<String>> expectedTableBody = Arrays.asList(Arrays.asList("6", "6", "40", "9.8"),
            // // Arrays.asList("7", "7", "41", "10.2"),
            //  Arrays.asList("8", "8", "42", "10.6"),
            // // Arrays.asList("9", "9", "43", "11"),
            //  Arrays.asList("10", "10", "44", "11.5"),
            // // Arrays.asList("11", "11", "45", "12.2"),
            //  Arrays.asList("12", "12", "46", "12.6"));


            for(int i=0;i<expectedTableBody.size();i++){
                List<String> rowdata = expectedTableBody.get(i); // Arrays.asList("7", "7", "41", "10.2") //0
                
                

                for(int j=0;j<rowdata.size();j++){
                    String expectedTablebodyValue = rowdata.get(j); //getting the each coulum value of each rows


                    int row =i+1;
                    int column =j+1;

                 WebElement element = driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[" + column + "]"));
                 String actualTableValue = element.getText();

                 if(!expectedTablebodyValue.equals(actualTableValue)){
                   status = false;
                 }

                }
            }

            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false


            WebElement sizedropdown = driver.findElement(By.xpath("//select[@name='age']"));
            status = sizedropdown.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}
