package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Allen").withMiddlename("Middlename")
              .withLastname("Jones").withNickname("Nick").withAddress("Address").withHomePhone("25 178 89")
              .withMobilePhone("+7(912) 455 22 00").withWorkPhone("254 55 33").withEmail("111@mail.ru")
              .withEmail2("222@mail.ru").withEmail3("@mail.ru")
              .withPhoto(new File("src/test/resources/camera.png")).withGroup("test1"));

    }
  }


  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Allen")
            .withMiddlename("Middlename").withLastname("Jones").withNickname("Nick")
            .withAddress("Address").withHomePhone("25 178 89").withMobilePhone("+7(912) 455 22 00")
            .withWorkPhone("254 55 33").withEmail("111@mail.ru")
            .withEmail2("222@mail.ru").withEmail3("@mail.ru")
            .withPhoto(new File("src/test/resources/camera.png")).withGroup("test1");
    app.contact().modify(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    // assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact)));
    assertThat(after, equalTo(
            before.without(modifiedContact).withAdded(contact)));
  }

}
