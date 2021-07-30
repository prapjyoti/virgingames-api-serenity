package com.virgingames.testinfo;

import com.virgingames.stepinfo.VirginGamesInfo;
import com.virgingames.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SerenityRunner.class)
public class VirginGamesTest extends TestBase {

    static String defaultGameFrequency;
    static String timeStamp;
    static String startTime;

    //This test will verify that all Games extracted has default games frequency = 300000
    @Steps
    VirginGamesInfo virginGamesInfo;

    @Title("This test will get the all virgin games frequency = 300000")
    @Test
    public void test001() {
        ValidatableResponse response = virginGamesInfo.getAllVirginGame().statusCode(200);

        defaultGameFrequency = response.extract().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency").toString();
        Assert.assertTrue(defaultGameFrequency.contains("300000"));
        System.out.println("Default Game Frequency are :" + defaultGameFrequency);
    }

    @Title("verify that the startTime is always future timeStamp")
    @Test
    public void test002() {
        //This test will verify that the startTime for all Games is always future timestamp.
        //  i.e. startTimes are greater than timeStamp.

        ValidatableResponse response = virginGamesInfo.getAllVirginGame()
                .statusCode(200);
        timeStamp = response.extract().path("timestamp").toString();
        startTime = response.extract().path("bingoLobbyInfoResource.streams.startTime").toString();

        assertThat(startTime, greaterThan(timeStamp));

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The timestamp is : " + timeStamp);
        System.out.println("The startTimes are :" + startTime);
        System.out.println("------------------End of Test---------------------------");
    }
    }
