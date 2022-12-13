import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RegistrationTest {

    private String generatedDate (int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shouldHappyPath() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 15;
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name='name']").setValue("Ирина Иванова");
        $("[name='phone']").setValue("+78888888888");
        String todayDate = generatedDate(7, "dd.MM.yyyy");
        $("[data-test-id='date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date] input").sendKeys(todayDate);

        $("label[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
       // $("notification__content");
        $("div[data-test-id=notification").shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("div[data-test-id=notification").shouldHave(Condition.exactText("Встреча успешно забронирована на " + todayDate));




    }
}
