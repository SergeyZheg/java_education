package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("SergeyZheg", "java_education")).commits();
        for(RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }

}