package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() throws Exception {
    app.getGroupHelper().selectContact();
    app.getGroupHelper().deleteSelectedContact();
  }


}
