package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();

  }

  public void selectContact(int index) {
    System.out.println("зашли в select   ");
    wd.findElements(By.name("selected[]")).get(index).click();

  }


  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));

  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new
              Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void inputContactCreation() {
    click(By.linkText("add new"));

  }

  public void submitContactModification() {
    wd.findElement(By.xpath("(//input[@name='update'])[1]")).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//a[contains(@href,'edit.php?id')]")).get(index).click();
  }


  public void create(ContactData contact) {
    inputContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void modify(int index, ContactData contact) {
    selectContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
  }


  public void delete(int index) {
    System.out.println("зашли в delete   ");
    selectContact(index);
    deleteSelectedContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Details']"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//img[@alt='Details']")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> elements = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("td")).
              findElement(By.tagName("input")).getAttribute("value"));
      String lastname = elements.get(1).getText();
      String firstname = elements.get(2).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));

    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> elements = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("td")).
              findElement(By.tagName("input")).getAttribute("value"));
      String lastname = elements.get(1).getText();
      String firstname = elements.get(2).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));

    }
    return contacts;
  }
}

