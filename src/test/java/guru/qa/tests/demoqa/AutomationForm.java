package guru.qa.tests.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import guru.qa.tests.helpers.Attachments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
    @AfterEach
    void addAttachments(){
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
    }
    @Test
    void successFillTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue("Testing First Name");
        $("#lastName").setValue("Testing Last Name");
        $("#userEmail").setValue("Testmail@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("35554556589");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1994");
        $("[aria-label$='March 5th, 1994']").click();
        $("#subjectsInput").setValue("Commerce").pressEnter();
        $("#hobbiesWrapper").$(Selectors.byText("Music")).click();
//        $("#uploadPicture").uploadFile (new File("src/test/resources/1.png"));
        $("#currentAddress").setValue("Testograd");
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(Selectors.byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(Selectors.byText("Noida")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(Condition.text("Thanks for submitting the form"));
        $(".table-responsive").$(Selectors.byText("Student Name")).parent().shouldHave(Condition.text("Testing First Name Testing Last Name"));
        $(".table-responsive").$(Selectors.byText("Student Email")).parent().shouldHave(Condition.text("Testmail@test.com"));
        $(".table-responsive").$(Selectors.byText("Gender")).parent().shouldHave(Condition.text("Male"));
    }
}

