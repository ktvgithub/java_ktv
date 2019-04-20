package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      System.out.println("зашли в before в контакты");
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstname("Firstname").withLastname("Lastname"));
    }
      if (app.db().groups().size() == 0) {
      System.out.println("зашли в before в группы");
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 11"));
    }

  }

  @Test
  public void deleteContactFromGroup() {
    app.goTo().contactPage();
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().insideGroup(groups.iterator().next().getName());
    if (!app.group().isThereAGroup()) {
      app.goTo().clickLogo();
      app.contact().addToGroup(contact, group);
      app.goTo().contactPage();
      app.goTo().insideGroup(groups.iterator().next().getName());
    }
    app.contact().deleteContactFromGroup(contact);
    groups.removeAll(contact.getGroups());
    assertThat(contact.getGroups(), CoreMatchers.not(CoreMatchers.hasItem(group)));
    app.db().refresh(contact);
  }
}

