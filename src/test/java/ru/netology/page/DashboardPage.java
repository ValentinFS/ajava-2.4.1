package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }


    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement cardFist = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private SelenideElement cardSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
    private SelenideElement cardButtonFirst = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] button");
    private SelenideElement cardButtonSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] button");


    public int getFirstCardBalance() {
        String text = cardFist.getText();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        String text = cardSecond.getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage pushButtonFirst() {
        cardButtonFirst.click();
        return new TransferPage();
    }

    public TransferPage pushButtonSecond() {
        cardButtonSecond.click();
        return new TransferPage();
    }

}
