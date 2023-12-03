package pl.kwolszczak.selenium7_2_3.configuration.driver;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import pl.kwolszczak.selenium7_2_3.configuration.browser.Browser;
import pl.kwolszczak.selenium7_2_3.configuration.driver.setup.SetupChrome;
import pl.kwolszczak.selenium7_2_3.configuration.driver.setup.SetupEdge;
import pl.kwolszczak.selenium7_2_3.configuration.driver.setup.SetupFirefox;


@Slf4j
public class DriverFactory {

    @Getter
    private WebDriver driver;
    private String browserName = "chrome";
    private final String url;
    private Browser browser;

    public DriverFactory() {
        url = System.getProperty("environment.url");
        initBrowser();
        initDriver();
        navigateUrl();
    }

    private void initBrowser() {
        browserName = System.getProperty("browser.name") == null ? browserName : System.getProperty("browser.name");
        browser = Browser.get(browserName);
    }

    private void initDriver() {
        log.info("#### Setup {} browser", browserName);
        switch (browser) {
            case CHROME -> driver = new SetupChrome().getDriver();
            case FIREFOX -> driver = new SetupFirefox().getDriver();
            case EDGE -> driver = new SetupEdge().getDriver();
            default -> driver = new SafariDriver();
        }
    }

    private void navigateUrl() {
        log.info("Open appUrl from config {}", url);
        driver.get(url);
    }
}
