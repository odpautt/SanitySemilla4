package com.indra.actions;

import com.indra.models.DataExcelModels;
import com.indra.pages.PortabilityPostActivationPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

public class PortabilityPostActivationActions extends PortabilityPostActivationPage {
    DataExcelModels dataExcelModels = new DataExcelModels();
    DatabasePortInActions databasePortInActions = new DatabasePortInActions();
    public PortabilityPostActivationActions(WebDriver driver) {
        super(driver);
    }

    public void solicitudNip(String msisdnPort) throws SQLException {
        getPreventa().click();
        getPortabilidadNumerica().click();
        getSolicitudes().click();
        getSolicitudNip().click();
        enter(msisdnPort).into(getInputMsisdn());
        getBtnSolicitar().click();
        WebElement soliNip = getDriver().findElement(By.id("frmSlctdPin:j_id42"));
        MatcherAssert.assertThat("solicitud nip exitosa",
                soliNip.getText(),Matchers.containsString("Las solicitudes se procesaron") );
    }

    public void validateLinesBd() throws SQLException {
        databasePortInActions.cleanLinesMsisdn(dataExcelModels.getMsisdnPort());
        databasePortInActions.cleanLinesMsi(dataExcelModels.getMsiPort());
    }

    public void validateTransctionBd() throws SQLException {
        databasePortInActions.executePortabilityTransactionStatus(dataExcelModels.getMsiPort());
    }

    public void aceptNitBd() throws SQLException {
        databasePortInActions.executePortabilityNip(dataExcelModels.getMsiPort());
    }

    public void consultNipBd() throws SQLException {
        databasePortInActions.executeSelectNip(dataExcelModels.getNip());
    }

    public void executePortabilityReceptBd() throws SQLException {
        databasePortInActions.executePortabilityRecept(dataExcelModels.getMsisdnPort());
    }

    public void executeUpdatePortIdBd() throws SQLException {
        databasePortInActions.executeUpdatePortId(dataExcelModels.getMsiPort(), dataExcelModels.getMsisdnPort());
    }

    public void executePortIdBd() throws SQLException {
        databasePortInActions.executePortId(dataExcelModels.getPortId());
    }

    public void executePortabilityTransactionBd() throws SQLException {
        databasePortInActions.executePortabilityTransaction(dataExcelModels.getPortId());
    }

    public void executeWindowPortabilityBd() throws SQLException {
        databasePortInActions.executeWindowPortability(dataExcelModels.getMsisdnPort());
    }

    public void initialRute(String msisdnPort) throws SQLException {
        validateLinesBd();
        consultSingleScreen(msisdnPort);
        validateTransctionBd();
        solicitudNip(msisdnPort);
        validateTransctionBd();
        aceptNitBd();
        validateTransctionBd();
        consultNipBd();
    }

    public void initialPortability(){
        getSale().click();
        getDropdownActivation().click();
        getDropdownPay().click();
        getActivator().click();
        WebElement iframe = getDriver().findElement(By.id("iframe"));
        getDriver().switchTo().frame(iframe);
        getDropdownActivator().click();
        getPortabilityPospaid().click();
    }

    public void customerInformation(String vendedor,String cliente)  {
        //enter("10960370").into(getVendor());
        enter(vendedor).into(getVendor());
        getButtonId().click();
        getDocumentType().click();
        //enter("667299000").into(getDocumentCC());
        enter(cliente).into(getDocumentCC());
        enter("2000").into(getDocumentExpedicion());
        getBtnContinue().click();
    }

    public void activationPortability(String nip,String msisdnPort, String msisdn, String msi){
        getTypeTel().click();
        getPospago().click();
        enter(nip).into(getNip());
        getTypeSol().click();
        getTypeSol1().click();
        enter(msisdnPort).into(getMsisdnPort());
        getCalendarizada().click();
        getCalendar().click();
        selectNextBusinessDayFromCalendar();
        enter(msi).into(getMsi());
        enter(msisdn).into(getMsisdn());
        getSimSola().click();
        getDriver().switchTo().defaultContent();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,420)"); //Scroll vertically down by 1000 pixels
        WebElement iframe = getDriver().findElement(By.id("iframe"));
        getDriver().switchTo().frame(iframe);
        waitABit(500);
        WebElement continuar = getDriver().findElement(By.name("ActivacionesForm:btnContinuarActivacionVenta"));
        continuar.click();
    }

    public  void demographicInformation(){
        enter("Salazar londonio").into(getDistrict());
        getDropdownDeparment().click();
        getDeparment().click();
        getDropdownCity().click();
        getCity().click();
        enter("3222345678").into(getPhone());
        enter("3222345679").into(getAlternatePhone());
        enter("pruebaAutoma@gmail.com").into(getMail());
        getDate().click();
        getChooseDate().click();
        getMonth().click();
        getChooseYear().click();
        getChooseYear().click();
        getYear().click();
        getDateOk().click();
        getDay().click();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,420)"); //Scroll vertically down by 1000 pixels
        getElectronicBill().click();
        getContinueDemo().click();
        //getContinueSale().click();
        waitABit(2000);
        //getConfirm().click();
        //getActivationDetails().waitUntilPresent();
        WebElement title = getDriver().findElement(By.className("tituloPagina"));
        MatcherAssert.assertThat("La activacion fue exitosa",title.getText(), Matchers.equalTo("ACTIVACION EXITOSA"));
    }

    public void selectNextBusinessDayFromCalendar(){
        int currentDay=0;// se usa para indicar cuando llego al dia actual del calendario
        List<WebElement> dias = getDriver().findElements(By.xpath("//td[@onmouseover]")); // almacena todos los dias presentes del calendario
        // ciclo para recorrer la lista de dias del calendario
        for(WebElement dia :dias){
            //si el contador currentDay es igual 1 y es el dia seguiente le hace click (solo selecciona los dias habiles)
            if(currentDay==1 && dia.getAttribute("class").equals("rf-cal-c-cnt-overflow rf-cal-c rf-cal-btn") )
            {
                System.out.println("selecciono este día "+ dia.getText());
                dia.click();
                break;
            }
            // si el dia del calendario es igual al dia presente hace el contador currentDay igual a 1.
            if(dia.getAttribute("class").equals("rf-cal-c-cnt-overflow rf-cal-c rf-cal-today rf-cal-btn")){
                currentDay=1;
            }

        }
    }


    public void consultSingleScreen(String msisdn){
        getDriver().switchTo().defaultContent();
        getConsult().click();
        getConsultPos().click();
        getConsultIntegral().click();
        getCosultaPantallaUnica().click();
        WebElement iframe = getDriver().findElement(By.id("iframe"));
        getDriver().switchTo().frame(iframe);
        enter(msisdn).into(getMsisdn2());
        getSearchButton().click();
        getGeneralCustomerInformation().waitUntilPresent();
        WebElement plan = getDriver().findElement(By.id("j_id15:j_id29"));

        MatcherAssert.assertThat("la informacion",
                plan.getText(),Matchers.containsString("No se encontró información del cliente") );
    }

    public void consultSingleScreen1(String msisdn){
        getDriver().switchTo().defaultContent();
        getConsult().click();
        getConsultPos().click();
        getConsultIntegral().click();
        getCosultaPantallaUnica().click();
        WebElement iframe = getDriver().findElement(By.id("iframe"));
        getDriver().switchTo().frame(iframe);
        //enter("3016875893").into(getMsisdn2());
        enter(msisdn).into(getMsisdn2());
        getSearchButton().click();
        waitABit(1000);
        getGeneralCustomerInformation().waitUntilPresent();
        WebElement plan = getDriver().findElement(By.id("j_id135:j_id157"));

        MatcherAssert.assertThat("el plan es prepago",
                plan.getText(),Matchers.containsString("Plan Tigo Prepago") );
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,420)"); //Scroll vertically down by 1000 pixels
        getHlr().click();
        WebElement hrl = getDriver().findElement(By.id("j_id473:j_id477"));
        MatcherAssert.assertThat("el hrl es ",
                plan.getText(),Matchers.containsString("Operation is successful") );
    }

    public void consultSingleScreen2(String msisdn){
        getDriver().switchTo().defaultContent();
        getConsult().click();
        getConsultPos().click();
        getConsultIntegral().click();
        getCosultaPantallaUnica().click();
        WebElement iframe = getDriver().findElement(By.id("iframe"));
        getDriver().switchTo().frame(iframe);
        //enter("3016875893").into(getMsisdn2());
        enter(msisdn).into(getMsisdn2());
        getSearchButton().click();
        waitABit(1000);
        getGeneralCustomerInformation().waitUntilPresent();
        WebElement plan = getDriver().findElement(By.id("j_id135:j_id157"));

        MatcherAssert.assertThat("el plan es prepago",
                plan.getText(),Matchers.containsString("Plan Tigo Prepago") );
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,420)"); //Scroll vertically down by 1000 pixels
        getHlr().click();
        WebElement hrl = getDriver().findElement(By.id("j_id473:j_id477"));
        MatcherAssert.assertThat("el hrl es ",
                plan.getText(),Matchers.containsString("Operation is successful") );
    }
}
