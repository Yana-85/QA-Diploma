package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    //  TEST DATA

    //  Card Number

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getEmptyCardNumberField() {
        return "";
    }

    public static String getIncorrectCardNumberWithOneDigit() {
        return "4";
    }

    public static String getIncorrectCardNumberWithFifteenDigits() {
        return "4444 4444 4444 444";
    }

    public static String getInvalidCardNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getAnotherBankFieldCardNumber() {
        return "4444 4444 4444 4443";
    }

    //  Month

    public static String getValidMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getInvalidMonthIfFieldZeros() {
        return "00";
    }

    public static String getIncorrectMonthWithOneDigit() {
        return "1";
    }

    public static String getEmptyMonthField() {
        return "";
    }

    //  Year

    public static String getValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getExpiredYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidYearIfFieldZeros() {
        return "00";
    }

    public static String getIncorrectYearWithOneDigit() {
        return "0";
    }

    public static String getEmptyYearField() {
        return "";
    }

    //  Owner

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidOwnerFieldOnCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidOwnerFieldWithOneWord() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    public static String getInvalidOwnerFieldWithThreeWords() {
        return "Ivanov Ivan Ivanovich";
    }

    public static String getInvalidOwnerFieldWithLowerCase() {
        return "ivan ivanov";
    }

    public static String getInvalidOwnerFieldWithUpperCase() {
        return "IVAN IVANOV";
    }

    public static String getInvalidOwnerFieldWithOneLetter() {
        return "A";
    }

    public static String getInvalidOwnerFieldWithLotsNumberOfLetters() {
        return "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    }

    public static String getInvalidOwnerFieldWithDigits() {
        return "12345";
    }

    public static String getInvalidOwnerFieldWithSymbols() {
        return "!@#$%^&?*";
    }

    public static String getEmptyOwnerField() {
        return "";
    }

    //  CVC/CVV

    private static String getValidCVC() {
        Faker faker = new Faker();
        return faker.numerify("###");
    }

    public static String getInvalidCVC() {
        return "000";
    }

    public static String getIncorrectCVCWithTwoDigits() {
        return "12";
    }

    public static String getIncorrectCVCWithOneDigit() {
        return "1";
    }

    public static String getEmptyFieldCVC() {
        return "";
    }

    //  Testing the Card Number

    public static CardInfo getValidApprovedCardData() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getValidDeclinedCardData() {
        return new CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getCardNumberFieldEmpty() {
        return new CardInfo(getEmptyCardNumberField(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidCardNumberWithOneDigit() {
        return new CardInfo(getIncorrectCardNumberWithOneDigit(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidCardNumberWithFifteenDigits() {
        return new CardInfo(getIncorrectCardNumberWithFifteenDigits(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidCardNumberIfFieldAllZeros() {
        return new CardInfo(getInvalidCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getAnotherBankCardNumber() {
        return new CardInfo(getAnotherBankFieldCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    //  Testing a Month

    public static CardInfo getFieldMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), getEmptyMonthField(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidMonthWithZeros() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonthIfFieldZeros(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidMonthWithIrrelevantValue() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonth(), getValidYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidMonthIsCurrentYear() {
        return new CardInfo(getApprovedCardNumber(), getLastMonth(), getCurrentYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidMonthWithOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getIncorrectMonthWithOneDigit(), getValidYear(), getValidOwner(), getValidCVC());
    }

    //  Testing Year

    public static CardInfo getFieldYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getEmptyYearField(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidYearWithOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getIncorrectYearWithOneDigit(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidYearWithZeros() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getInvalidYearIfFieldZeros(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getCardWithExpiredYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getExpiredYear(), getValidOwner(), getValidCVC());
    }

    public static CardInfo getInvalidYearExceedingCardExpirationDate() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getInvalidYear(), getValidOwner(), getValidCVC());
    }

    //  Testing the Owner

    public static CardInfo getOwnerFieldEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getEmptyOwnerField(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithOneLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithOneLetter(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithOneWord() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithOneWord(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithThreeWords() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithThreeWords(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithLotsNumberOfLetters() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithLotsNumberOfLetters(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithLowerCase() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithLowerCase(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithUpperCase() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithUpperCase(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldOnCyrillic(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithDigits() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithDigits(), getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithSymbols() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getInvalidOwnerFieldWithSymbols(), getValidCVC());
    }

    //  Testing CVC/CVV

    public static CardInfo getEmptyCVCField() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getEmptyFieldCVC());
    }

    public static CardInfo getInvalidCVCWithOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getIncorrectCVCWithOneDigit());
    }

    public static CardInfo getInvalidCVCWithTwoDigits() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getIncorrectCVCWithTwoDigits());
    }

    public static CardInfo getInvalidCVCWithZeros() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getInvalidCVC());
    }
}