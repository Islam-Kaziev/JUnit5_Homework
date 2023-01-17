import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class itProgerTest {

    @BeforeEach
    void setUp() {
        open("https://itproger.com/practice/linux");
    }
//    @CsvFileSource(
//            resources = "example.csv"
//    )
    static Stream<Arguments> itProgerTest() {
        return Stream.of(
                        Arguments.of("Файлы и директории", List.of( "Linux - Файлы и директории")),
                        Arguments.of("Пакетный менеджер", List.of( "Linux - Пакетный менеджер")),
                        Arguments.of("Работа с файлами", List.of("Linux - Работа с файлами")),
                        Arguments.of("Основы", List.of( "Linux - Основы"))
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Сравнение соответствия названия вкладок с заголовками")
    @Tag("BLOCKER")
    void itProgerTest(String tab, List<String> titles) {
        $$("#navigation li").find(text(tab)).click();
    $$("aside h1").filter(visible).shouldHave(CollectionCondition.texts(titles));
    }
}
