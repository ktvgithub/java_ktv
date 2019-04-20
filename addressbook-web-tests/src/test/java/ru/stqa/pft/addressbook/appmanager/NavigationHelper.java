package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Group")
            && isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }
  }

  public void contactPage() {
   if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));
    }
  }

  public void insideGroup(String groupName) {
      Select value = new Select(wd.findElement(By.name("group")));
      value.selectByVisibleText(groupName);
    }

  public void clickLogo() {
    click(By.id("logo"));
  }

  }

