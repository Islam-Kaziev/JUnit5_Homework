import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MtsTest {

    @BeforeEach
    void setUp() {
        open("https://spb.mts.ru/personal/dlya-doma/checkconnect/spb-city/all-mts");
    }

    @CsvSource({
            "Ленинский пр-кт, 147 к 3, доступен для подключения",
            "Ленинский пр-кт, 147 к 1, Пока не можем подключить услуги по адресу"
    }

    )
    @ParameterizedTest(name = "Проверка доступных тарифов на адресе {0}" + " вместе с  {1} и {2}")
    @Tag("BLOCKER")
        void MtsTest(String street, String house, String resultText) {

        $x("//input[@placeholder='Название улицы']").setValue(street);
        $x("//input[@placeholder='Номер дома'] ").setValue(house);
        $x("//span[contains(text(), 'Проверить')]/..").click();
     $x("//p[contains(@class, 'connect-address-form-result')]").shouldHave(text(resultText));
    }

    @Disabled("Тут можно указать номер задачи") //этот тест будет пропущен
    @DisplayName("Проверка попапа загрузки фото")
    @Test
    @Tag("BLOCKER")
    void googlePhotoPopupTest() {

        $("img[alt='Поиск с помощью камеры']").click();
        $(byText("Выполните поиск по изображению в Google Объективе")).shouldBe(visible);
    }
}
