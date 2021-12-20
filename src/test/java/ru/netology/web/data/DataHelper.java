package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    public static DataGenerator dataGenerator = new DataGenerator();

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    //   Testing the Card Number

    public static CardInfo getValidApprovedCardData() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getValidDeclinedCardData() {
        return new CardInfo(DataGenerator.getDeclinedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getCardNumberFieldEmpty() {
        return new CardInfo(DataGenerator.getEmptyCardNumberField(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidCardNumberWithOneDigit() {
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithOneDigit(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidCardNumberWithFifteenDigits() {
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithFifteenDigits(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidCardNumberIfFieldAllZeros() {
        return new CardInfo(DataGenerator.getInvalidCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getAnotherBankCardNumber() {
        return new CardInfo(DataGenerator.getAnotherBankFieldCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    //  Testing a Month

    public static CardInfo getFieldMonthEmpty() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getEmptyMonthField(), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthWithZeros() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonthIfFieldZeros(), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthWithIrrelevantValue() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonth(), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthIsCurrentYear() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(-1), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthWithOneDigit() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getIncorrectMonthWithOneDigit(), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    //  Testing Year

    public static CardInfo getFieldYearEmpty() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getEmptyYearField(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidYearWithOneDigit() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getIncorrectYearWithOneDigit(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidYearWithZeros() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getInvalidYearIfFieldZeros(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getCardWithExpiredYear() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(-1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidYearExceedingCardExpirationDate() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(6), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    //  Testing the Owner

    public static CardInfo getOwnerFieldEmpty() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getEmptyOwnerField(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithOneLetter() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getInvalidOwnerFieldWithOneLetter(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithOneWord() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getInvalidOwnerFieldWithOneWord(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithThreeWords() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithThreeWords(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithLotsNumberOfLetters() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getInvalidOwnerFieldWithLotsNumberOfLetters(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithLowerCase() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getInvalidOwnerFieldWithLowerCase(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithUpperCase() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithUpperCase(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithCyrillic() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getInvalidOwnerFieldOnCyrillic(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithDigits() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getInvalidOwnerFieldWithDigits(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithSymbols() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithSymbols(), DataGenerator.getValidCVC());
    }

    //  Testing CVC/CVV

    public static CardInfo getEmptyCVCField() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getEmptyFieldCVC());
    }

    public static CardInfo getInvalidCVCWithOneDigit() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithOneDigit());
    }

    public static CardInfo getInvalidCVCWithTwoDigits() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithTwoDigits());
    }

    public static CardInfo getInvalidCVCWithZeros() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getInvalidCVC());
    }
}