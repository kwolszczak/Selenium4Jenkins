package pl.kwolszczak.selenium7_2_3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class SimpleTest  {


    @Test
    @DisplayName("1. Selenium - smoke test")
    void verify_webSelenium_hasTitle() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        String expectedTitle = "Selenium";
        String title = driver.getTitle();
        System.out.println(title);
        Assertions.assertThat(title).isEqualTo(expectedTitle);
    }

}
