package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigatorHelper {
  	import mantis.model.UserData;
	import org.openqa.selenium.By;

  public class NavigationHelper extends HelperBase{



    public NavigationHelper(ApplicationManager app){
      super(app);
    }

    public void login(){
      wd.get(app.getProperty("webBaseUrl"));
      type(By.name("username"),app.getProperty("userName"));
      type(By.name("password"),app.getProperty("password"));
      click(By.xpath("//*[@type = 'submit']"));
    }
    public void openManageUsers(){
      click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']"));
      click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public void choiceUser(UserData account) {

      click(By.cssSelector("a[href='manage_user_edit_page.php?user_id="+ account.getId()+"']"));
    }
    public void resetPassword() {
      click(By.xpath("//*[@value= 'Reset Password']"));

    }
  }




}
