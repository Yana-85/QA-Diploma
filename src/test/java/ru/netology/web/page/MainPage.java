package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement buyButton = $$(".button").first(); // $(byText("Купить"));
    private final SelenideElement buyButtonOnCredit = $$(".button").last(); // $(byText("Купить в кредит"));
    private final SelenideElement paymentByCard = $$("h3.heading").find(Condition.exactText("Оплата по карте"));
    private final SelenideElement creditCardData = $$("h3.heading").find(Condition.exactText("Кредит по данным карты"));

    public MainPage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentPage getDebitCardPayment() {
        buyButton.click();
        paymentByCard.shouldBe(Condition.visible);
        return new PaymentPage();
    }

    public PaymentPage getPaymentByCreditCard() {
        buyButtonOnCredit.click();
        creditCardData.shouldBe(Condition.visible);
        return new PaymentPage();
    }
}
