package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void deleteSelectedContact() {
    wd.switchTo().alert().accept();
  }

  public void selectContact() {
    click(By.name("selected[]"));
    click(By.xpath("//input[@value='Delete']"));

  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));

  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void inputContactCreation() {
    click(By.linkText("add new"));

  }

  public void submitContactModification() {


    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void initContactModification() {

    click(By.xpath("(//img[@alt='Edit'])[2]"));
  }

  public void createContact(ContactData contact) {
    inputContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("(//img[@alt='Edit'])[2]"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//img[@alt='Details']")).size();
  }
}