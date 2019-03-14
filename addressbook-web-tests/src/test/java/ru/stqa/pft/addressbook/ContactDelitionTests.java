package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() throws Exception {
    app.selectContact();
    app.deleteSelectedContact();
  }


}
