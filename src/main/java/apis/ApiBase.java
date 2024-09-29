package apis;

import com.shaft.driver.SHAFT;

public class ApiBase {

    private SHAFT.API api;

    public ApiBase(SHAFT.API api) {
        this.api = api;
    }

    public static String ApiBaseURL = System.getProperty("baseUrl")+"/api";

    public static final int SUCCESS = 200;
}
