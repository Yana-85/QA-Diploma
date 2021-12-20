package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;
import ru.netology.web.sql.SqlHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardPaymentTest {

    MainPage mainPage;

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.cleanDb();
    }

    @BeforeEach
    public void setUp() {
        mainPage = open(System.getProperty("sut.url"), MainPage.class);
    }

    // POSITIVE SCENARIOS

    @Test
    public void shouldDoPaymentByCreditCardWithStatusApproved() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getValidApprovedCardData();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkSuccessNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWithStatusDeclined() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getValidDeclinedCardData();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    // NEGATIVE SCENARIOS

    // Card Number

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldWithInvalidCardNumber() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidCardNumberIfFieldAllZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenCardNumberFieldAnotherBankCard() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getAnotherBankCardNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldCardNumberWithOneDigit() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidCardNumberWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldCardNumberWithFifteenDigits() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidCardNumberWithFifteenDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenCardNumberFieldEmpty() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getCardNumberFieldEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    // Month

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldWithInvalidMonth() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidMonthWithIrrelevantValue();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenMonthFieldIsZeros() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidMonthWithZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldWithMonthExpiredCurrentYear() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidMonthIsCurrentYear();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyCardExpired();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldMonthOneDigit() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidMonthWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldMonthEmpty() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getFieldMonthEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    //  Year

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldWithExpiredYear() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getCardWithExpiredYear();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyCardExpired();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldWithInvalidYearExpirationDate() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidYearExceedingCardExpirationDate();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenYearFieldWithZeros() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidYearWithZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenYearFieldWithOneDigit() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidYearWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenYearFieldEmpty() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getFieldYearEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    // Owner

    @Test
    public void shouldNotDoPaymentByCreditCardWhenOwnerFieldFilledCyrillic() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithCyrillic();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenOwnerFieldWithOneLatinWord() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithOneWord();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenOwnerFieldWithThreeLatinWords() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithThreeWords();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenOwnerFieldWithLowerCase() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithLowerCase();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenOwnerFieldWithUpperCase() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithUpperCase();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerFieldWithOneLetter() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithOneLetter();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerFieldWithLargeNumberLetters() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithLotsNumberOfLetters();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerFieldWithDigits() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerFieldWithSymbols() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidOwnerWithSymbols();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerFieldEmpty() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getOwnerFieldEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyEmptyField();
    }

    //  CVC/CVV

    @Test
    public void shouldNotDoPaymentByCreditCardWhenCVCIsOneDigit() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidCVCWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenCVCIsTwoDigits() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidCVCWithTwoDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfCVCWithThreeZeros() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getInvalidCVCWithZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfCVCFieldEmpty() {
        val paymentPage = mainPage.getPaymentByCreditCard();
        val info = DataHelper.getEmptyCVCField();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
}
