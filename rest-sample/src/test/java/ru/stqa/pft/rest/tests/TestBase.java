package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
 //       skipIfNotFixed(Integer.parseInt(app.getProperty("soap.issueId")));
    }

    boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> testedIssue = app.rest().getIssueById(issueId);
        String issueStatus = testedIssue.iterator().next().getState_name();
        if (issueStatus.equals("Resolved")) {
            return false;
        } else if (issueStatus.equals("Closed")) {
            return false;
        } else return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


}
