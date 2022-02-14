package com.indra.steps_definitions;

import com.indra.actions.*;
import com.indra.models.DataExcelModels;
import com.indra.models.LoginEposModels;
import com.indra.models.LoginPortalCRMModels;
import com.indra.models.WindexModels;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class SanitySteps{
    @Managed
    WebDriver driver;

    DataExcelModels dataExcelModels = new DataExcelModels();
    ResourceEnlistmentActions enlistment = new ResourceEnlistmentActions();
    LoginEposPageActions loginPageAction = new LoginEposPageActions(driver);
    MerchandiseEntryAction merchandiseEntryAction = new MerchandiseEntryAction(driver);
    InventoryAllocationActions inventoryAllocationActions = new InventoryAllocationActions(driver);
    LoginPortalCRMActions loginPortalCRMActions = new LoginPortalCRMActions(driver);
    InventoryActivationActions activationAction = new InventoryActivationActions();
    PrepaidActivationActions prepaidActivationActions = new PrepaidActivationActions(driver);
    CesionPortalCRMActions cesionActions = new CesionPortalCRMActions(driver);
    int Activation =0;
    ControlActivationActions controlActivationActions = new ControlActivationActions(driver);

//-----------<Primer escenario>----------------
    @Given("^Se ejecutan procedimientos en bd y soapUi$")
    public void seEjecutanProcedimientosEnBdYSoapUi() throws SQLException {
        enlistment.executeAllProcedures();
    }

    @When("^Se ingresa a la plataforma epos para cargue de inventario$")
    public void seIngresaALaPlataformaEposParaCargueDeInventario(List<LoginEposModels> loginPageModels) {
        loginPageAction.open();
        loginPageAction.clickOnLogin(loginPageModels.get(0));
    }


    @Then("^Se ingresa a entrada masiva de mercancia$")
    public void seIngresaAEntradaMasivaDeMercancia() {
        merchandiseEntryAction.loadMerchandise();
    }

    @Then("^Se completa datos para cargar mercancia$")
    public void seCompletaDatosParaCargarMercancia() throws InterruptedException {
        merchandiseEntryAction.merchandiseEntry();
        merchandiseEntryAction.merchandiseEntryInventory();
        Thread.sleep(2000);
        loginPageAction.leave();
    }

    //-----------<Segundo escenario>----------------

    @When("^Se ingresa a cargue de inventario$")
    public void seIngresaACargueDeInventario() throws InterruptedException {
        inventoryAllocationActions.loadInventory();
        Thread.sleep(2000);
        inventoryAllocationActions.leaveSesion();

    }

    @Then("^Deberia poder realizar el cargue de inventario$")
    public void deberiaPoderRealizarElCargueDeInventario() {
    }

    //-----------<Tercer escenario>----------------

    @Given("^se ingresa a la plataforma epos windex$")
    public void seIngresaALaPlataformaEposWindex(List<WindexModels> windexModels) throws IOException, InterruptedException, AWTException, IOException, AWTException {
        Activation= activationAction.executeStepsActivation(windexModels.get(0));
    }

    @Then("^se deberia poder ver mensaje de confimacion exitosa$")
    public void seDeberiaPoderVerMensajeDeConfimacionExitosa() {
        assertThat("finaliza la confirmacion de inventario",Activation, Matchers.is(1));
    }

    //-----------<Cuarto escenario>----------------

    @Given("^Se ingresa al portal CRM para activacion$")
    public void seIngresaAlPortalCRMParaActivacion(List<LoginPortalCRMModels> loginPortalCRMModels) {
        driver.get(dataExcelModels.getUrlCRM());
        loginPortalCRMActions.clickOnLogin(loginPortalCRMModels.get(0));
    }

    @When("^Se hace activacion de una linea en prepago$")
    public void seHaceActivacionDeUnaLineaEnPrepago() throws InterruptedException {
        prepaidActivationActions.initialRute();
        prepaidActivationActions.customerInformation(dataExcelModels.getVendedorPrepago()
                , dataExcelModels.getCedulaClientePrepago());
        prepaidActivationActions.activationInformation(dataExcelModels.getMsisdnPrepago(),dataExcelModels.getMsiPrepago());
        prepaidActivationActions.demographicInformation();
    }

    @Then("^Se deberia ver en pantalla unica la linea activa en prepago$")
    public void seDeberiaVerEnPantallaUnicaLaLineaActivaEnPrepago() {
        prepaidActivationActions.consultSingleScreen(dataExcelModels.getMsisdnPrepago());
    }
    //-----------<escenario>----------------

    @When("^Se hace la cesion de contrato de una linea$")
    public void seHaceLaCesionDeContratoDeUnaLinea() throws InterruptedException, AWTException {
        cesionActions.initialRute();
        cesionActions.executeContractAssignment();

    }


    @Then("^Se deberia ver en pantalla unica la linea cedida$")
    public void seDeberiaVerEnPantallaUnicaLaLineaCedida() {
        prepaidActivationActions.consultSingleScreen2(dataExcelModels.getMsisdnPrepago());
    }


    //-----------<Quinto escenario>----------------

    @When("^Se hace activacion de una linea en control$")
    public void seHaceActivacionDeUnaLineaEnControl() throws InterruptedException {
        controlActivationActions.initialRute();
        controlActivationActions.customerInformation(dataExcelModels.getVendedorPostpago()
                , dataExcelModels.getCedulaClientePostpago());
        controlActivationActions.activationInformation(dataExcelModels.getMsisdnPostpago(),dataExcelModels.getMsiPostpago());
        controlActivationActions.demographicInformation();
    }

    @Then("^Se deberia ver en pantalla unica la linea activa en control$")
    public void seDeberiaVerEnPantallaUnicaLaLineaActivaEnControl() {
        controlActivationActions.consultSingleScreen(dataExcelModels.getMsisdnPostpago());
    }

}



