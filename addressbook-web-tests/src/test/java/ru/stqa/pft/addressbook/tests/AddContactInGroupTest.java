package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactInGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstname("Firstname").withLastname("Lastname"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 11"));
    }
  }

    @Test
  public void testContactAddInGroup() {

    app.goTo().contactPage();
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    app.goTo().insideGroup(groups.iterator().next().getName());
    if( app.group().isThereAContact()){
      app.contact().deleteContactFromGroup(contact);
    }
    GroupData group = groups.iterator().next();
    groups.removeAll(contact.getGroups());
    app.goTo().clickLogo();
    app.contact().addToGroup(contact,group);
    app.db().refresh(contact);
    assertThat(contact.getGroups(),CoreMatchers.hasItem(group));
     }
  }

