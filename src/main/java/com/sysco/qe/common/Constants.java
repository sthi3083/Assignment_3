package com.sysco.qe.common;

public class Constants {

    /*
    QCenter Properties according to the Assignment 03 Guidlines
     */
    public static final String TEST_PROJECT = System.getProperty("test.project", "Brakes_Hybris_B2B");
    public static final String TEST_ENV = System.getProperty("test.env", "QA");
    public static String TEST_RELEASE = System.getProperty("test.release", "TEMP_TEST");
    public static final boolean UPDATE_DASHBOARD = Boolean.parseBoolean(System.getProperty("update.dashboard", "true"));
    public static String QLYTICS_DAILY_WEEKLY_BUILD = System.getProperty("daily.weekly.build","daily");
    public static final boolean IS_CREATE_BUILD_ENABLED = Boolean.parseBoolean(System.getProperty("create.build", "true"));
    public static final String TEST_LAYER = System.getProperty("test.layers");
    public static String QLYTICS_BUILD_COMPLETION = System.getProperty("build.completion", "true");

    /*
    QMetry Properties
     */
    public static final boolean UPDATE_QMETRY = Boolean.parseBoolean(System.getProperty("update.qmetry", "false"));
    public static final String QMETRY_PROJECT_NAME = System.getProperty("qmetry.project.name", "Testing Platform");
    public static final String QMETRY_TEST_CYCLE_KEY = System.getProperty("qmetry.test.cycle.key", "TQSV-TR-27942");
    public static final String QMETRY_DEFAULT_CYCLE_NAME = System.getProperty("qmetry.default.cycle.name", "");
    public static final String SYSCO_QMETRY_TOKEN = System.getProperty("qmetry.token", "");
    public static final String QMETRY_DYNAMIC_CYCLE = System.getProperty("qmetry.dynamic.cycle", "");
    public static final String QMETRY_CUSTOM_FIELD_MAP = System.getProperty("qmetry.custom.fields.map","{\"Automatable\":\"Yes\",\"Automated\":\"Yes\",\"QCenter Tracker\":\"SUTAP Regression\"}");

    /*
    Email Notification Settings
    */
//    public static final boolean EMAIL_SEND = Boolean.parseBoolean(System.getProperty("send.email", "false"));
//    public static final String EMAIL_RECIPIENT = System.getProperty("email.recipient", "");

    /*
    Test Data Management (TDM) Settings
     */
//    public static final boolean TDM_VALIDATION = Boolean.parseBoolean(System.getProperty("tdm.validation", "false"));
//    public static final String TDM_TOKEN = "";
//    public static final String TDM_FILE = "";
//    public static final String[] TDM_CHECKING_COLUMN = {"Name"};

    /*
    General Application Settings
     */
    public static final String APP_URL = "https://www.brakesce.co.uk/";
    public static String BROWSER = System.getProperty("browser", "chrome");

}