package com.sysco.qe.util;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sysco.qe.common.Constants;
import com.sysco.qe.functions.Home;
import com.sysco.qeutils.common.Constant;
import com.syscolab.qe.core.common.LoggerUtil;
import com.syscolab.qe.core.common.TestLayers;
import com.syscolab.qe.core.playwright.ui.BaseBrowser;
import com.syscolab.qe.core.reporting.SyscoLabListener;
import static com.sysco.qe.common.Constants.*;
import static com.syscolab.qe.core.common.TestLayers.API;
import static com.syscolab.qe.core.common.TestLayers.ETL;
import static com.syscolab.qe.core.reporting.SyscoLabReporting.generateBuild;
import com.syscolab.qe.core.reporting.SyscoLabQCenter;
import com.syscolab.qe.core.reporting.SyscoLabReporting;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Listeners(SyscoLabListener.class)
public class TestBase extends BaseBrowser {

    protected SoftAssert softAssert = new SoftAssert();
    private SyscoLabListener testListeners;
    protected SyscoLabQCenter syscoLabQCenter;
    List<String> failedOrSkippedTCsList = new ArrayList<>();
    public static final String QCENTER_FEATURE = "feature";
    String testID = "";

    @BeforeClass(alwaysRun = true)
    public void init() {
        testListeners = new SyscoLabListener();
        syscoLabQCenter = new SyscoLabQCenter();
        Home.loadHomePage();
    }

    @BeforeSuite(alwaysRun = true)
    public void configureReporting() {
        if (Constants.UPDATE_DASHBOARD) {
            System.setProperty("update.dashboard", String.valueOf(Constants.UPDATE_DASHBOARD));
            System.setProperty("daily.weekly.build", String.valueOf(Constants.QLYTICS_DAILY_WEEKLY_BUILD));
            System.setProperty("build.completion", String.valueOf(Constants.QLYTICS_BUILD_COMPLETION));
            System.setProperty("test.layers", String.valueOf(Constants.TEST_LAYER));
            System.setProperty("test.project", String.valueOf(TEST_PROJECT));
            System.setProperty("test.env", String.valueOf(TEST_ENV));
            System.setProperty("test.release", String.valueOf(Constants.TEST_RELEASE));
        }
        if (Constants.UPDATE_QMETRY) {
            System.setProperty("update.qmetry", String.valueOf(Constants.UPDATE_QMETRY));
            System.setProperty("qmetry.project.name", Constants.QMETRY_PROJECT_NAME);
            System.setProperty("qmetry.test.cycle.key", Constants.QMETRY_TEST_CYCLE_KEY);
            System.setProperty("qmetry.token", Constants.SYSCO_QMETRY_TOKEN);
            System.setProperty("qmetry.default.cycle.name", Constants.QMETRY_DEFAULT_CYCLE_NAME);
            System.setProperty("qmetry.dynamic.cycle", Constants.QMETRY_DYNAMIC_CYCLE);
            System.setProperty("qmetry.custom.fields.map", Constants.QMETRY_CUSTOM_FIELD_MAP);
        }
    }
    @BeforeSuite(alwaysRun = true)
    public static void initializeReportData() {
        System.setProperty("update.dashboard", String.valueOf(Constants.UPDATE_DASHBOARD));
        System.setProperty("daily.weekly.build", String.valueOf(Constants.QLYTICS_DAILY_WEEKLY_BUILD));
        System.setProperty("test.project", String.valueOf(TEST_PROJECT));
        System.setProperty("test.env", String.valueOf(Constant.TEST_ENV));
        System.setProperty("test.release", String.valueOf(Constants.TEST_RELEASE));
        System.setProperty("build.completion", String.valueOf(Constants.QLYTICS_BUILD_COMPLETION));
//        System.setProperty("test.suite", TEST_SUITE);
    }

    @AfterMethod(alwaysRun = true)
    public void updateQCenter(ITestContext iTestContext, ITestResult testResult) {
        try {
            syscoLabQCenter.setProjectName(TEST_PROJECT);
            syscoLabQCenter.setEnvironment(TEST_ENV);
            syscoLabQCenter.setRelease(TEST_RELEASE);
            if(iTestContext.getAttribute(TEST_LAYER).toString().equals(ETL)){
                syscoLabQCenter.setTestLayer(ETL);}
            else {
                if (iTestContext.getAttribute(TEST_LAYER).toString().equals(API)) {
                    syscoLabQCenter.setTestLayer(API);
                }else{
                    syscoLabQCenter.setTestLayer(TestLayers.OTHER);
                }
            }
            syscoLabQCenter.setModule(iTestContext.getAttribute(QCENTER_FEATURE).toString());
            syscoLabQCenter.setFeature(iTestContext.getAttribute(QCENTER_FEATURE).toString());
            syscoLabQCenter.setClassName(iTestContext.getClass().getName());
            if (UPDATE_DASHBOARD) {
                LoggerUtil.logINFO("************** Updating Dashboard *****************");
                JsonObject tcResult = SyscoLabReporting.getElement(testResult, null);

                String status = tcResult.get("steps").getAsJsonArray().get(0).getAsJsonObject().get("result").getAsJsonObject().get("status").getAsString();
                testID = tcResult.get("id").getAsString();
                JsonArray array = new JsonArray();
                if(status.equalsIgnoreCase("failed")) {
                    failedOrSkippedTCsList.add(testID);
                }
                if(failedOrSkippedTCsList.contains(testID)) {
                    tcResult.get("steps").getAsJsonArray().get(0).getAsJsonObject().get("result").getAsJsonObject().addProperty("status", "failed");
                }
                array.add(tcResult);
                SyscoLabReporting.generateJsonFile(array, syscoLabQCenter);
            }
        } catch (Exception e) {
            LoggerUtil.logINFO(Arrays.toString(e.getStackTrace()));
        }
    }

    public void createQCenterBuild() throws JSONException {
        if (IS_CREATE_BUILD_ENABLED) {
            generateBuild();
        }
    }
    @BeforeSuite
    public void runBeforeSuite() {
        initializeReportData();
        createQCenterBuild();

    }

    @AfterClass(alwaysRun = true)
    // Close and quit the browser after each test
    public void cleanAfterTest(ITestContext iTestContext) {
        try {
            Home.quitApplication();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}