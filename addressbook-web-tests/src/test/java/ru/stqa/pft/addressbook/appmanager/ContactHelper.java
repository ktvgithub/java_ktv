package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  private String firstname;
  private String lastname;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void deleteSelectedContact() {
    wd.switchTo().alert().accept();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
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


    click(By.xpath("(//input[@name='update'])[1]"));
  }

  public void initContactModification() {

    click(By.xpath("(//img[@alt='Edit'])[1]"));
  }

  public void create(ContactData contact) {
    inputContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
   }

  public void delete(int index) {
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
    WebElement table_element = wd.findElement(By.cssSelector(("table.sortcompletecallback-applyZebra")));
    List<WebElement> rows = table_element.findElements(By.tagName("tr"));
    int n = 0;
    for (WebElement row : rows) {
      if (n > 0) {
        int id = Integer.parseInt(row.findElement(By.tagName("td")).
                  findElement(By.tagName("input")).getAttribute("value"));
        int i = 0;
        List<WebElement> cells = row.findElements(By.tagName("td"));
        for (WebElement cell : cells) {
          if (i == 1) lastname = cells.get(1).getText();
          if (i == 2) firstname = cells.get(2).getText();
          i++;
        }
          ContactData contact = new ContactData(id, firstname, null, lastname,
                null, null, null, null, null,
                null, null, null);
        contacts.add(contact);
      }
      n++;
    }
    return contacts;
  }

}






