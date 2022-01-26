package severstal.allurereport;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class issuesTest {
    reportTest steps = new reportTest();

    @Test
    @Owner("Saiferin")
    @Feature("Issues")
    @Story("Проверка вкладкуи Issues")
    @DisplayName("Проверка вкладку Issues")
    public void listenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("Saiferin/qaFormNew");
        $(".header-search-input").submit();

        $(linkText("Saiferin/qaFormNew")).click();
        $("#issues-tab").should(Condition.visible);
    }

    @Test
    @Owner("Saiferin")
    @Feature("Issues")
    @Story("Проверка вкладку Issues")
    @DisplayName("Проверка  вкладки Issues с лямбдой")
    public void lamdaTest() {

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репу " + "Saiferin/qaFormNew", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys("Saiferin/qaFormNew");
            $(".header-search-input").submit();
        });

        step("Переходим в репу", () -> {
            $(linkText("Saiferin/qaFormNew")).click();
        });

        step("Открываем Issues", () -> {
            $(partialLinkText("Issues")).click();
        });

        step("Проверяем Issue", () -> {
            $("#issues-tab").should(Condition.visible);
        });
    }

    @Test
    @Owner("Saiferin")
    @Feature("Issues")
    @Story("Проверка вкладку Issues")
    @DisplayName("Проверка наличия вкладки Issues с аннотациями")
    public void annotatedStepTest() {
        steps.openMainPage();
        steps.searchForRepository("Saiferin/qaFormNew");
        steps.openRepositoryPage("Saiferin/qaFormNew");
        steps.openIssuesTab();
    }

    @AfterEach
    public void saveSources() {
        steps.attachPageSource();
    }
}
