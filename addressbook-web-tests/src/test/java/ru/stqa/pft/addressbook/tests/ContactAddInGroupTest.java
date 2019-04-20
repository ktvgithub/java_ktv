package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


public class ContactAddInGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0)
    {  app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactAddInGroup() {
// извлекаем все контакты
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
   System.out.println("contacts.size() " + contacts.size());
    System.out.println("contacts " + contacts);
    System.out.println("groups.size() " + groups.size());
    System.out.println("groups " + groups);
    //System.out.println("groups " + groups.getName());
   //System.out.println("groups " + groups.getNmae());
// выбираем произвольный контакт
    ContactData addContact = contacts.iterator().next();
    System.out.println("addContact  "+addContact);
    System.out.println("addContact.getGroups  " + addContact.getGroups());
    System.out.println("addContact.getGroups.size  " + addContact.getGroups().size());
   if (groups.size() == addContact.getGroups().size()) {
     app.group().create(new GroupData().withName("test10"));
     //GroupData dat = new GroupData().withName("test10");
    // groups.add(dat);


     //     app.goTo().contactPage();
//     app.contact().modify(addContact);
    // app.goTo().contactPage();
    // new Select(wd.findElement(By.name("new_group")))
     //        .selectByVisibleText(addContact.getGroups().iterator().next().getName());

   } else {
     //(groups.containsAll(addContact))

   }


    // System.out.println("addContact.getId()  "+addContact.getId());
  }

}
