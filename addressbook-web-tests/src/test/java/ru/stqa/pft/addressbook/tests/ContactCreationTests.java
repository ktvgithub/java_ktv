package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/camera.png");
    ContactData contact = new ContactData().withFirstname("Allen").withLastname("Jones").withGroup("test1")
            .withPhoto(photo);
    app.contact().create(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

 // @Test
 // public void testCurrentDir() {
 //   File cuurentDir = new File(".");
 //   System.out.println(cuurentDir.getAbsolutePath());
 //   File photo = new File("src/test/resources/camera.png");
 //   System.out.println(photo.getAbsolutePath());
 //   System.out.println(photo.exists());
 // }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Allen'").withLastname("Jones").withGroup("test1");
    app.contact().create(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }

}
