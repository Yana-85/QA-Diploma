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

public class DebitCardPaymentTest {

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
    public void shouldDoPaymentByDebitCardWithStatusApproved() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getValidApprovedCardData();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkSuccessNotification();
        val paymentStatus = SqlHelper.getStatusPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWithStatusDeclined() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getValidDeclinedCardData();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
        val paymentStatus = SqlHelper.getStatusPaymentEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    // NEGATIVE SCENARIOS

    // Card Number

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldWithInvalidCardNumber() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidCardNumberIfFieldAllZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenCardNumberFieldAnotherBankCard() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getAnotherBankCardNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldCardNumberWithOneDigit() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidCardNumberWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldCardNumberWithFifteenDigits() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidCardNumberWithFifteenDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenCardNumberFieldEmpty() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getCardNumberFieldEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    // Month

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldWithInvalidMonth() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidMonthWithIrrelevantValue();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenMonthFieldIsZeros() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidMonthWithZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldWithMonthExpiredCurrentYear() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidMonthIsCurrentYear();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyCardExpired();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldMonthOneDigit() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidMonthWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldMonthEmpty() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getFieldMonthEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    //  Year

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldWithExpiredYear() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getCardWithExpiredYear();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyCardExpired();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldWithInvalidYearExpirationDate() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidYearExceedingCardExpirationDate();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenYearFieldWithZeros() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidYearWithZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenYearFieldWithOneDigit() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidYearWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenYearFieldEmpty() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getFieldYearEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    // Owner

    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerFieldFilledCyrillic() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithCyrillic();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerFieldWithOneLatinWord() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithOneWord();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerFieldWithThreeLatinWords() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithThreeWords();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerFieldWithLowerCase() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithLowerCase();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerFieldWithUpperCase() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithUpperCase();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerFieldWithOneLetter() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithOneLetter();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerFieldWithLargeNumberLetters() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithLotsNumberOfLetters();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerFieldWithDigits() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerFieldWithSymbols() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidOwnerWithSymbols();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerFieldEmpty() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getOwnerFieldEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyEmptyField();
    }

    //  CVC/CVV

    @Test
    public void shouldNotDoPaymentByDebitCardWhenCVCIsOneDigit() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidCVCWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenCVCIsTwoDigits() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidCVCWithTwoDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfCVCWithThreeZeros() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getInvalidCVCWithZeros();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfCVCFieldEmpty() {
        val paymentPage = mainPage.getDebitCardPayment();
        val info = DataHelper.getEmptyCVCField();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
}