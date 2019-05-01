package ru.stqa.pft.mantis.tests;

import org.hibernate.service.spi.ServiceException;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class CreateIssueNotFixed extends TestBase{


  @Test
  public void testCreateIssueNotFixed() throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
    skipIfNotFixed(9);
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

}



