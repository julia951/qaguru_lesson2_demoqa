import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class RegistrationTest {

    private final String firstName= "Tester First Name";
    private final String lastName= "Tester First Name";
    private final String email= "tester@gmail.test";
    private final String phoneNumber= "1234567890";
    private final String month= "January";
    private final String year= "2000";
    private final String subjectMath= "Maths";
    private final String address= "testCity testStreet 123";
    private final String state= "Haryana";
    private final String city= "Panipat";
    private final String successModalWindow= "Thanks for submitting the form";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/automation-practice-form");

        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").val(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month").click();
        $("#subjectsInput").val(subjectMath).pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("./img/1.png");
        $("#currentAddress").val(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $(".modal-title").shouldHave(text(successModalWindow));
        $(".table-responsive").shouldHave(text(firstName+" "+lastName),text(email),text("Other"),
                text(phoneNumber),text("28 January,2000"),text(subjectMath),text("Reading"),text("1.png"),
                text(address),text(state +" "+ city));
    }
}
