package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() throws Exception {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Allen", "William",
              "Jones", "lion", "Hairdresser", "Harmony",
              "7, Oxford Street London W 15 NP Great Britain", "0 726 234 567 89",
              "+447800767690", "AWJ_harmony@mail.ru", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().returnToContactPage();
  }


}
