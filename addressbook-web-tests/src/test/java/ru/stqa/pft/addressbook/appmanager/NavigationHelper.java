package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
   if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Group")
           &&isElementPresent(By.name("new")))
    { return;
    } else {
    click(By.linkText("groups")); }
  }

  public void returnToContactPage() {
    if (isElementPresent(By.id("maintable")))
    {return;
    } else {click(By.linkText("home"));}
   }

}
