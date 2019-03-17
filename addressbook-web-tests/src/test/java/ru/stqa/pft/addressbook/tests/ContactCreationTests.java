package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation()throws Exception {
    app.getContactHelper().inputContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Allen", "William", "Jones", "lion", "Hairdresser", "Harmony", "7, Oxford Street London W 15 NP Great Britain", "0 726 234 567 89", "+447800767690", "AWJ_harmony@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToContactPage();
  }

}
