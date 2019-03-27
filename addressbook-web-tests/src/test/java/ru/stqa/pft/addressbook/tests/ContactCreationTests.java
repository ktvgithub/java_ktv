package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().returnToContactPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Allen", "William",
            "Jones", "lion", "Hairdresser", "Harmony",
            "7, Oxford Street London W 15 NP Great Britain", "0 726 234 567 89",
            "+447800767690", "AWJ_harmony@mail.ru", "test1"));

    app.getNavigationHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);

    after.remove(after.size() - 1);
    Assert.assertEquals(before, after);
  }

}
