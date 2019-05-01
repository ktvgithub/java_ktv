package ru.stqa.pft.rest.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {
  @BeforeClass
  public void init() {
    RestAssured.authentication =
            RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
  }

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }


  private Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();
            System.out.println("json" + json);
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance()
            .auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  private int createIssue(Issue newIssue) throws IOException {
     String  json = RestAssured.given().parameter("subject", newIssue.getSubject())
          .parameter("discription", newIssue.getDescription())
          .post("http://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


}
