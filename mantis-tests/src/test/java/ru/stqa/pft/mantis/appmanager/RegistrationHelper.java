package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.tests.TestBase;

public class RegistrationHelper extends BaseHelper {
//    private final ApplicationManager app;
//    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
//        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value = 'Signup']"));
//        click(By.cssSelector("input[value = 'Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String username, String password) {
        wd.get(confirmationLink);
        type(By.cssSelector("input[name = 'realname']"),username);
        type(By.cssSelector("input[name = 'password']"), password);
        type(By.cssSelector("input[name = 'password_confirm']"), password);
//        click(By.cssSelector("input[value = 'Update User']"));
        click(By.cssSelector("span[class = 'bigger-110']"));
    }
}
