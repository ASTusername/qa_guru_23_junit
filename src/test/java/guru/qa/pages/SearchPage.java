package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private final SelenideElement addressInput = $("#geo-suggest-input");
    private final SelenideElement addressGroupInput = $("[data-group='addresses']");
    private final SelenideElement searchBottom = $(withText("Найти"));
    private final ElementsCollection result = $$("[data-testid='offer-card']");

    public SearchPage openPage() {
        open("");
        return this;
    }

    public SearchPage setAddress(String value) {
        addressInput.setValue(value);
        //    addressGroupInput.find(withText(value)).click(ClickOptions.usingJavaScript());
        addressGroupInput.$(byTitle(value)).click();
        return this;
    }

    public void clickSearch() {
        searchBottom.parent().shouldBe(enabled).click();
    }

    public SearchPage checkResultList() {
        result.shouldBe(sizeGreaterThan(0));
        return this;
    }

    public SearchPage checkResult(String key, String value) {
        SelenideElement selector = null;
        switch (key) {
            case "PhoneButton":
                selector = $("[data-mark='PhoneButton']");
                break;
            case "MainPrice":
                selector = $("[data-mark='MainPrice']");
                break;
            case "PriceInfo":
                selector = $("[data-mark='PriceInfo']");
                break;
            case "Title":
                selector = $("[data-name='Breadcrumbs']");
                break;
            default:
                break;
        }
        selector.shouldHave(text(value));
        return this;
    }
}
