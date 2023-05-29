package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.ScheduledJobs;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.RequestObject.Section;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class SchedulePolicies {

    //second,minute,hour,day of the month, month, day of the week.
    //https://developer.nytimes.com/

    @PreAuthorize("hasRole('ADMIN')")
    @Scheduled(cron = "0 0 0 * * *")
    public static void cronForPolicies() throws IOException, InterruptedException, Exception {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=foreignaffairsministry_db;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String pass = "root";

        HttpClient hClient = HttpClient.newHttpClient();
        HttpRequest requestData = HttpRequest.newBuilder().uri(URI.create("https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=xfjwbyKIMp0lGS0Tn3DSFx3JvLo2iCBX"))
                .build();

        HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());

        Gson gson=new Gson();

        JsonParser jParser = new JsonParser();
        JsonElement jElement = jParser.parse(response.body());
        String jsonString = gson.toJson(jElement);
        //System.out.println(jsonString);

        Section data = gson.fromJson(jsonString, Section.class);

        Connection con = null;
        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        DriverManager.registerDriver(driver);
        con = (Connection) DriverManager.getConnection(url, user, pass);

        for (int i=0;i<data.getResults().toArray().length;i++) {
            String topic = data.getResults().get(i).getSection();
            String country = data.getResults().get(i).getSource();
            String region = data.getResults().get(i).getSection();
            String details = data.getResults().get(i).getSource();

            String SqlQuery = "INSERT INTO Policies (topic,country,region,details) VALUES"
                    + " ('" + topic + "' ,'" + country + "', '" + region +"', '"+ details + "')";

            try {
                Statement st = con.createStatement();
                int m1 = st.executeUpdate(SqlQuery);
                if (m1 >= 0)
                    System.out.println("policies table inserted new values successfully : " + SqlQuery);
                else
                    System.out.println("insertion failed");
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        con.close();
    }
}
