package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();

  }

   private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));

  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

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

  private void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
  }


  public void create(ContactData contact) {
    inputContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    ContactCache = null;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    ContactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    ContactCache = null;
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Details']"));
  }

  public int count() {
    return wd.findElements(By.xpath("//img[@alt='Details']")).size();
  }

  private Contacts ContactCache = null;

  public Contacts all() {
    if (ContactCache != null) {
      return new Contacts( ContactCache);
    }
    ContactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> elements = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("td")).
              findElement(By.tagName("input")).getAttribute("value"));
      String lastname = elements.get(1).getText();
      String firstname = elements.get(2).getText();
      String allPhones = elements.get(5).getText();
      String address = elements.get(3).getText();
      String allEmail = elements.get(4).getText();

      ContactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
              withAllPhones(allPhones).withAddress(address).withAllEmail(allEmail));

  }
    return new Contacts(ContactCache);
  }

  public ContactData infoFormEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    System.out.println(address);
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
            withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}

