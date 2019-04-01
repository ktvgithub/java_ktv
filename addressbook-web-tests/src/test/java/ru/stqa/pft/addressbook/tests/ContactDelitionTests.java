package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDelitionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions()  {
    app.goTo().contactPage();
   // if (app.contact().isThereAContact()) {
    if (app.group().list().size() == 1) {
      app.contact().create(new ContactData().withFirstname("Allen").withLastname("Jones").withGroup("test1"));
    }
  }

  @Test
  public void testContactDelition() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().contactPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);

  }

  private void delete(int index) {
    app.contact().selectContact(index);
    app.contact().deleteSelectedContact();
    app.goTo().contactPage();
  }


}
