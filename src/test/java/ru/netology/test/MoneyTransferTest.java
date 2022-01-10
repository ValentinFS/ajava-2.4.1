package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyFrom2To1Card() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verifyInfo);
//        System.out.println("Карта 1 начало = " + dashboardPage.getFirstCardBalance() + "   Карта 2 начало = " + dashboardPage.getSecondCardBalance());
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        TransferPage transferPage = dashboardPage.pushButtonFirst();
        DataHelper.CardsInfo cardsInfo = new DataHelper.CardsInfo();
        int sum = 100;
        transferPage.transferFromToOwnCards(sum, cardsInfo.getCardNumberSecond());
        int firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
//        System.out.println("Карта 1 конец = " + dashboardPage.getFirstCardBalance() + "   Карта 2 конец = " + dashboardPage.getSecondCardBalance());
        assertEquals(firstCardBalance + sum, firstCardBalanceAfterTransfer);
        assertEquals(secondCardBalance - sum, secondCardBalanceAfterTransfer);
    }

    @Test
    void shouldTransferMoneyFrom1To2Card() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verifyInfo);
        System.out.println("Карта 1 начало = " + dashboardPage.getFirstCardBalance() + "   Карта 2 начало = " + dashboardPage.getSecondCardBalance());
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        TransferPage transferPage = dashboardPage.pushButtonSecond();
        DataHelper.CardsInfo cardsInfo = new DataHelper.CardsInfo();
        int sum = 200;
        transferPage.transferFromToOwnCards(sum, cardsInfo.getCardNumberFirst());
        int firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
        System.out.println("Карта 1 конец = " + dashboardPage.getFirstCardBalance() + "   Карта 2 конец = " + dashboardPage.getSecondCardBalance());
        assertEquals(firstCardBalance - sum, firstCardBalanceAfterTransfer);
        assertEquals(secondCardBalance + sum, secondCardBalanceAfterTransfer);
    }
}