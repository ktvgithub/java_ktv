package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }


  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());

  }


  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  private void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    GroupCache = null;
    returnToGroupPage();

  }

  public boolean isAlertPresent() {
    return super.isAlertPresent();

  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    GroupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroup();
    GroupCache = null;
    returnToGroupPage();
  }


  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups GroupCache = null;
  public Groups all() {
     if (GroupCache != null) {
      return new Groups(GroupCache);
    }
    GroupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(GroupCache);
  }


}