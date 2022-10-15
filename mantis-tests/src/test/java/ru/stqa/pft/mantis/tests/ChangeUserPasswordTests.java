package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changeUserPasswordTest() throws MessagingException, IOException, InterruptedException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String login = String.format("user%s", now);
        String newPassword = "newPassword";


        app.changeUserPassword().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Thread.sleep(1000);

        Users users = app.db().getUsersWithoutAdmin();
        if (users.size() > 0) {
            UserData user = users.iterator().next();
            login = user.getName();
            email = user.getEmail();
        } else if (users.size() == 0) {
            app.changeUserPassword().start(login, email);
        }

        app.changeUserPassword().goToManageUsers(login);
        Thread.sleep(1000);
        List<MailMessage> mailMessages2 = app.mail().waitForMail(1, 1000);
        String confirmationLink2 = findConfirmationLink(mailMessages2, email);
        app.changeUserPassword().finish(confirmationLink2, newPassword);
        assertTrue(app.newSession().login(login, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.get(0);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
