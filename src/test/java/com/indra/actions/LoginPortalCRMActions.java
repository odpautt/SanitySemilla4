package com.indra.actions;

import com.indra.models.LoginPortalCRMModels;
import com.indra.pages.LoginPortalCRMPage;
import org.openqa.selenium.WebDriver;

public class LoginPortalCRMActions extends LoginPortalCRMPage {

    public String contraseniaInicial;

    public LoginPortalCRMActions(WebDriver driver) {
        super(driver);
    }

    public void fillLogin(LoginPortalCRMModels signIn)  {
        enter(signIn.getUser()).into(getUser());
        enter(signIn.getPassword()).into(getPassword());
    }

    public void clickOnLogin(LoginPortalCRMModels signIn){
        fillLogin(signIn);
        login();
    }

    public void login(){
        getBtnLogin().click();
    }

}
