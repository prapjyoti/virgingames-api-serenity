package com.virgingames.stepinfo;

import com.virgingames.constant.EndPoints;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class VirginGamesInfo {
//define all steps and methods for getting all the video games information

    @Step("Getting the all video game from data")
    public ValidatableResponse getAllVirginGame(){
        return  SerenityRest.rest()
                .given()
                .log()
                .all()
                .when()
                .get(EndPoints.GET_BINGO)
                .then().log().ifValidationFails()
                .parser("text/plain;charset=UTF-8", Parser.JSON);//parsing the text/plain in to jason

    }

}
