package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

  public class ChangeUserPasswordTest extends TestBase {
    @BeforeMethod
    public void startMailServer(){
      app.mail().start();
    }

    @Test
    public void changeUserPasswordTest() throws IOException {
      Users users = app.db().users();
      UserData selectedContact = users.iterator().next();
      app.navigate().login();
      app.navigate().openManageUsers();
      app.navigate().choiceUser(selectedContact);
      app.navigate().resetPassword();
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 15000);
      String resetLink = findResetLink(mailMessages, selectedContact.getEmail());
      String newpassword = "newpassword";
      app.registration().finish(resetLink, newpassword);
      assertTrue(app.newSession().login(selectedContact.getUsername(),newpassword));

    }
    private String findResetLink(List<MailMessage> messages, String email) {
      MailMessage message = messages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(message.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
      app.mail().stop();
    }
  }




}
