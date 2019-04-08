package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Allen").withLastname("Jones").withGroup("test1")
              .withHomePhone("22-45-55").withMobilePhone("+7(855)95612").withWorkPhone("122 35 55")
              .withAddress("455555, London").withEmail("111@mail").withEmail2("222@mail").withEmail3("333@mail"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
    app.goTo().contactPage();

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress().trim()));
    assertThat(contact.getAllEmail(), equalTo(mergeAllEmaile(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }

  private String mergeAllEmaile(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleanedMail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedMail(String phone) {
    return phone.replaceAll("\\s", "");

  }

}
