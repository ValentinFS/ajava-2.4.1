package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement buttonCancel = $("[data-test-id=action-cancel]");

    public DashboardPage transferFrom2to1(int sum, String from) {
        amount.doubleClick().sendKeys(Keys.DELETE);
        amount.setValue(Integer.toString(sum));
        fromCard.doubleClick().sendKeys(Keys.DELETE);
        fromCard.setValue(from);
        buttonTransfer.click();
        return new DashboardPage();
    }

    public static class Compare {
        private int sum;
        private int firstBefore;
        private int firstAfter;
        private int secondBefore;
        private int secondAfter;

        public Compare(int sum, int firstBefore, int firstAfter, int secondBefore, int secondAfter) {
            this.sum = sum;
            this.firstBefore = firstBefore;
            this.firstAfter = firstAfter;
            this.secondBefore = secondBefore;
            this.secondAfter = secondAfter;
        }

        public int compareBalanceFirst() {
            int resultFirstCard = Math.abs(firstAfter - firstBefore);
            return resultFirstCard;
        }

        public int compareBalanceSecond() {
            int resultSecondCard = Math.abs(secondAfter - secondBefore);
            return resultSecondCard;
        }
    }

}
