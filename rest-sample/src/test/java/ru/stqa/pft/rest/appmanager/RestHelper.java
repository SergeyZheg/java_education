package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app){
        this.app = app;
    }

//    Метод GET для REST запроса существующих issues из багтрекера bagify
    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("rest.testUrl"))).returnContent().asString();
//        JsonElement parsed = new JsonParser().parse(json);
        JsonElement parsed =  JsonParser.parseString(json); // Этой строкой предложено заменить строку выше
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("rest.apiKey"), "");
    }

    //    Метод POST для REST запроса добавления новой issue в багтрекер bagify
    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(app.getProperty("rest.testUrl"))
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
//        JsonElement parsed = new JsonParser().parse(json);
        JsonElement parsed =  JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

//    Метод GET для REST запроса issue по заданному ID из багтрекера bagify
    public Set<Issue> getIssueById(int issuedID) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issuedID))).returnContent().asString();
//        JsonElement parsed = new JsonParser().parse(json);
        JsonElement parsed =  JsonParser.parseString(json);
       JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType()); //Возвращаются только элементы, указанные в типе Issue
    }




}
