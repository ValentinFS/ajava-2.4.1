package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement buttonCancel = $("[data-test-id=action-cancel]");

    public DashboardPage transferFromToOwnCards(int sum, String from) {
        amount.doubleClick().sendKeys(Keys.DELETE);
        amount.setValue(Integer.toString(sum));
        fromCard.doubleClick().sendKeys(Keys.DELETE);
        fromCard.setValue(from);
        buttonTransfer.click();
        return new DashboardPage();
    }

}
