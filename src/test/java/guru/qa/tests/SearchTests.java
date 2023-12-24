package guru.qa.tests;

import guru.qa.pages.SearchPage;
import guru.qa.tests.constants.DataTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SearchTests extends TestBase {
    SearchPage searchPage = new SearchPage();
    DataTest dataTest = new DataTest();

    @ValueSource(strings = {
            "Москва", "Сочи, Краснодарский край"
    })
    @ParameterizedTest(name = "Для адреса {0} должен отдавать не пустой список карточек")
    @DisplayName("По адресу")
    void searchResultsShouldNotBeEmptyTest(String address) {
        searchPage.openPage()
                .setAddress(address)
                .clickSearch();

        searchPage.checkResultList()
                .checkResult("PhoneButton", dataTest.phone);
    }

    @CsvSource(value = {
            "Томск , Продажа 1-комн и 2-комн квартир в Томске",
            "Омск , Продажа 1-комн и 2-комн квартир в Омске"
    })
    @ParameterizedTest(name = "Для адреса {0} в заголовке должно быть {1}")
    @DisplayName("по дата-провайдеру CsvSource")
    void searchResultsShouldContainExpectedUrl(String address, String expectedData) {
        searchPage.openPage()
                .setAddress(address);
        searchPage.clickSearch();

        searchPage.checkResult("Title", expectedData)
                .checkResult("MainPrice", dataTest.mainPrice)
                .checkResult("PriceInfo", dataTest.priceInfo);
    }

    @CsvFileSource(resources = "/test_data/cian.csv")
    @ParameterizedTest(name = "Для адреса {0} в заголовке должно быть {1}")
    @DisplayName("по дата-провайдеру CsvFileSource")
    void searchResultsShouldContainExpectedUrlFromCsvFile(String address, String expectedData) {
        searchPage.openPage()
                .setAddress(address);
        searchPage.clickSearch();

        searchPage.checkResult("Title", expectedData);
    }
}
