package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.cian.ru/";
        Configuration.browserSize = "1520x1080";
        Configuration.pageLoadStrategy = "eager";
    }
}
