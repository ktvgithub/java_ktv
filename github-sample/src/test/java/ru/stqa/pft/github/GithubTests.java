package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {

    Github github = new RtGithub("699e1e24fb97eee6c83545c9a638dc4fdcfae535");
    RepoCommits commits = github.repos()
            .get(new Coordinates.Simple("ktvgithub", "java_ktv")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build()))  {
      System.out.println(new RepoCommit.Smart(commit).message());

    }
  }
}
