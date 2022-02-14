package com.indra.actions;

import com.indra.models.LoginEposModels;
import com.indra.pages.LoginEposPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginEposPageActions extends LoginEposPage {

    public String contraseniaInicial;

    public LoginEposPageActions(WebDriver driver) {
        super(driver);
    }

    public void fillLogin(LoginEposModels signIn)  {
        enter(signIn.getUser()).into(getUser());
        enter(signIn.getPassword()).into(getPassword());
    }

    public void clickOnLogin(LoginEposModels signIn){
        fillLogin(signIn);
        login();
    }

    public void login(){
        getBtnLogin().click();
    }

    public void leave(){
        Actions actions = new Actions(getDriver());
        WebElement leave = getDriver().findElement(By.id("formMenu:j_id11_span"));
        actions.moveToElement(leave).build().perform();
        getBtnLeave().click();
    }
}
