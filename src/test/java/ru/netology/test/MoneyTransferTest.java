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
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verifyInfo);
//        System.out.println("Карта 1 начало = " + dashboardPage.getFirstCardBalance() + "   Карта 2 начало = " + dashboardPage.getSecondCardBalance());
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.pushButtonFirst();
        TransferPage transferPage = new TransferPage();
        DataHelper.CardsInfo cardsInfo = new DataHelper.CardsInfo();
        int sum = 100;
        transferPage.transferFrom2to1(sum, cardsInfo.getCardNumberSecond());
        int firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
//        System.out.println("Карта 1 конец = " + dashboardPage.getFirstCardBalance() + "   Карта 2 конец = " + dashboardPage.getSecondCardBalance());
        TransferPage.Compare result = new TransferPage.Compare(sum, firstCardBalance, firstCardBalanceAfterTransfer, secondCardBalance, secondCardBalanceAfterTransfer);
        assertEquals(100, result.compareBalanceFirst());
    }

    @Test
    void shouldTransferMoneyFrom1To2Card() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verifyInfo);
//        System.out.println("Карта 1 начало = " + dashboardPage.getFirstCardBalance() + "   Карта 2 начало = " + dashboardPage.getSecondCardBalance());
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.pushButtonSecond();
        TransferPage transferPage = new TransferPage();
        DataHelper.CardsInfo cardsInfo = new DataHelper.CardsInfo();
        int sum = 200;
        transferPage.transferFrom2to1(sum, cardsInfo.getCardNumberFirst());
        int firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
//        System.out.println("Карта 1 конец = " + dashboardPage.getFirstCardBalance() + "   Карта 2 конец = " + dashboardPage.getSecondCardBalance());
        TransferPage.Compare result = new TransferPage.Compare(sum, firstCardBalance, firstCardBalanceAfterTransfer, secondCardBalance, secondCardBalanceAfterTransfer);
        assertEquals(200, result.compareBalanceSecond());
    }
}