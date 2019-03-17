package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToContactPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Allen", "William", "Jones", "lion", "Hairdresser", "Harmony", "7, Oxford Street London W 15 NP Great Britain", "0 726 234 567 89", "+447800767690", "AWJ_harmony@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToContactPage();
  }
}
