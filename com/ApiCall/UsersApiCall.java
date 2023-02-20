package com.ApiCall;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UsersApiCall {
    public JSONObject getUser(String value) {
        JSONObject jsonObject = null;
        try {
            URL url = new URL("http://10.52.0.126:8050/ProApp/user/getusers?id=" + value);// url of the api
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// opening the url connection using
                                                                                    // the HttpUrlConnection class
            connection.setRequestMethod("GET");// setting the request method as GET
            connection.connect();// connection the HttpUrlConnection object
            int responseCode = connection.getResponseCode();// getting the response code to check
            if (responseCode != 200) {// if the status code not equal to 200 throws a exception
                throw new RuntimeException("ResponseCode = " + responseCode);
            } else {
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();
                if (value.trim().equalsIgnoreCase("all")) {
                    JSONArray jsonArray = (JSONArray) new JSONParser().parse(String.valueOf(sb));
                } else {
                    jsonObject = (JSONObject) new JSONParser().parse(String.valueOf(sb));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        UsersApiCall uac = new UsersApiCall();
        System.out.println(uac.getUser("3"));
    }
}
