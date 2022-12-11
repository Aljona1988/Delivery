import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;

import java.time.Duration;


public class RegistrationTest {

    @Test
    public void Test1() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 15;
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name='name']").setValue("Ирина Иванова");
        $("[name='phone']").setValue("+78888888888");
        $("[placeholder='Дата встречи']").setValue("15.12.2022");
        $("label[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("div[data-test-id=notification").shouldBe(Condition.visible, Duration.ofMillis(15000));


    }
}
