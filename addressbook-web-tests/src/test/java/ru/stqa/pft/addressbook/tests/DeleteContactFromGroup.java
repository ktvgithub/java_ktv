package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      System.out.println("зашли в before в контакты");
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstname("Firstname").withLastname("Lastname"));
    }
    //app.goTo().contactPage(); }
    //app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      System.out.println("зашли в before в группы");
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 11"));
    }

  }

  @Test
  public void deleteContactFromGroup() {
    //app.goTo().contactPage();
    // Contacts contacts = app.db().contacts();
    // System.out.println("contacts " + contacts);
    // ContactData contact = app.db().contacts().iterator().next();
    // System.out.println("contact " + contact);
    // Groups groups = app.db().groups();
    // System.out.println("groups " + groups);
    // GroupData group = groups.iterator().next();
    // System.out.println("group " + group);
    // System.out.println("group.getName() " + group.getName());

//      app.goTo().contactPage();
//      ContactData contact = app.db().contacts().iterator().next();
//      Groups groups = app.db().groups();
//      GroupData group = groups.iterator().next();
//      app.goTo().insideGroup(groups.iterator().next().getName());
//        if(! app.group().isThereContact()){
//         app.goTo().backLogo();
//         app.contact().addToGroup(contact,group);
//        app.goTo().insideGroup(groups.iterator().next().getName());
//      }
//
//      app.contact().deleteContactFromGroup(contact);
//      groups.removeAll(contact.getGroups());
//      app.db().refresh(contact);
    //assertThat(contact.getGroups(), CoreMatchers.not(CoreMatchers.hasItem(group)));
    //  }


    app.goTo().contactPage();
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().insideGroup(groups.iterator().next().getName());
    if (! app.group().isThereAGroup()) {
      app.goTo().clickLogo();
      app.contact().addToGroup(contact, group);
      app.goTo().contactPage();
      app.goTo().insideGroup(groups.iterator().next().getName());
    }

    app.contact().deleteContactFromGroup(contact);
    groups.removeAll(contact.getGroups());
    app.db().refresh(contact);
    System.out.println("assertThat(contact.getGroups() " + contact.getGroups());
    System.out.println(" CoreMatchers.not(CoreMatchers.hasItem(group)) " + CoreMatchers.hasItem(group));

    // assertThat(contact.getGroups(), CoreMatchers.not(CoreMatchers.hasItem(group)));

  }
}

