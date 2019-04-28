package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

  public class NavigatorHelper extends HelperBase{

    public NavigatorHelper(ApplicationManager app){
      super(app);
    }

    public void login(){
      wd.get(app.getProperty("web.baseUrl"));
      type(By.name("username"),app.getProperty("web.loginAdmin"));
      click(By.xpath("//*[@type = 'submit']"));
      type(By.name("password"),app.getProperty("web.loginPassword"));
      click(By.xpath("//*[@type = 'submit']"));



    }
    public void openManageUsers() {
      wd.get(app.getProperty("web.baseUrl") +"/account_page.php");
      wd.findElement(By.cssSelector("i.menu-icon.fa.fa-gears")).click();
      wd.get(app.getProperty("web.baseUrl") +"/manage_overview_page.php");
      wd.findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();
    }

    public void choiceUser(UserData account) {
      wd.get(app.getProperty("web.baseUrl") +"/manage_user_page.php");
      wd.findElement(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_id="+ account.getId()+"')]")).click();
     }

      public void resetPassword() {
      wd.findElement(By.xpath("//input[@value='Reset Password']")).click();

    }
  }





