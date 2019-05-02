package ru.stqa.pft.rest.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

 public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
    System.out.println("json" + json);
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  public int createIssue(Issue newIssue) throws IOException {
    String  json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor()
            .execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parse = new JsonParser().parse(json);
    JsonElement issues = parse.getAsJsonObject().get("issues");
    JsonElement issue = issues.getAsJsonArray().get(0);
    JsonElement issue_state= issue.getAsJsonObject().get("state_name");
    String s = issue_state.getAsString();
    return s.equals("Open");
  }
  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }




}
