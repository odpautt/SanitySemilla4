package com.indra.actions;

import com.indra.models.DataExcelModels;
import com.indra.pages.PortabilityPrepaidPage;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

public class PortabilityPrepaidActions extends PortabilityPrepaidPage {
    public PortabilityPrepaidActions(WebDriver driver) {
        super(driver);
    }

    DataExcelModels dataExcelModels = new DataExcelModels();
    DatabasePortInActions databasePortInActions = new DatabasePortInActions();
    UninstallCBSServicesActions servicesActions = new UninstallCBSServicesActions();

    public void makePortabilityRequestAndDB(String msisdnPort) throws SQLException {
        validateLinesBd();
        consultSingleScreen(msisdnPort);
        validateTransctionBd();

        switchToDefaultContent();
        initialRuteRequestPortability();
        makePortabilityRequest(msisdnPort);

        validateTransctionBd();
        aceptNitBd();
        validateTransctionBd();

        MatcherAssert.assertThat("el status es PIN_REQUEST_ACEPTADO",
                validateTransctionBd(),Matchers.equalTo("PIN_REQUEST_ACEPTADO"));

        consultNipBd();


        System.out.println();
    }



    public void initialRute(){
        switchToDefaultContent();
        clickSale();
        clickActivation();
        clickPagoAplazos();
        clickActivator();
        switchToIframe();
        getListaDesplegable().waitUntilVisible();
        clickListArrow();
        clickPortabilityPrepaid();
    }

    public void customerInformation(String vendedor,String cliente)  {
        enter(vendedor).into(getIdAsesor());
        getListaTipoIdentificacion().click();
        getTipoCC().click();
        enter(cliente).into(getNumIdentificacion());
        enter("2000").into(getAnioExpedicion());
        getBtncontinuar().click();
    }

    public void activationInformation(String msisdnPorting,String msisdn, String imsi) throws SQLException {

        String nip = msisdnPorting.substring(5,10);
        consultNipBd();
        // logica si esta sumar 1 al nip
        // nip = String.valueOf(Integer.valueOf(nip) + 1);
        // repertir consulta
        String portId= "000022011082401"+nip;

        //System.out.println(nip +" *****"+ portId);


        clickCurrentTelephony();
        clicktypeTelephony();
        // generacion aleatorio
        writeNIP(nip);
        writeMsisdnToPorting(msisdnPorting);
        clickCheckScheduledDate();
        clickInputCalendar();
        selectNextBusinessDayFromCalendar();
        writeMsisdn(msisdn);
        writeImsi(imsi);
        clickTypeOfSale();
        clickTypeOfSaleOnlySim();
        windowsScrolldown();
        clickBtnContinue();
        waitABit(5000);
    }

    public void clickSale(){
        getVenta().click();
    }

    public void clickActivation(){
        getActivacion().click();
    }

    public void clickPagoAplazos(){
        getPagoAplazos().click();
    }

    public void clickActivator(){
        getActivador().click();
    }

    public void switchToIframe(){
        WebElement iframe = getDriver().findElement(By.id("iframe"));
        getDriver().switchTo().frame(iframe);
    }

    public void switchToDefaultContent(){
        getDriver().switchTo().defaultContent();
    }

    public void clickListArrow(){
        getListaDesplegable().click();

    }

    public void clickPortabilityPrepaid(){
        getPortabilidadPrepago().click();

    }

    public void clickCurrentTelephony(){
        getListaDesplegableTipoTelefonia().click();

    }

    public void clicktypeTelephony(){
        getTipoPrepago().click();

    }

    public void writeNIP(String NIP){
        enter(NIP).into(getInputNip());
    }

    public void writeMsisdnToPorting(String msisdnPorting){
        enter(msisdnPorting).into(getInputMsisdnAportar());
    }

    public void writeMsisdn(String msisdn){
        enter(msisdn).into(getInputMsisdn());
    }

    public void writeImsi(String imsi){
        enter(imsi).into(getInputImsi());
    }

    public void clickTypeOfSale(){
        getListaDesplegableTipoVenta().click();
    }

    public void clickTypeOfSaleOnlySim(){
        getTipoSimSola().click();
    }

    public void clickCheckScheduledDate(){
        getCheckFechaCalendarizada().click();
    }

    public void clickInputCalendar(){
        getInputcalendario().click();

    }

    public void selectNextBusinessDayFromCalendar(){
        int currentDay=0;// se usa para indicar cuando llego al dia actual del calendario
        int click = 0;
        List<WebElement> dias = getDriver().findElements(By.xpath("//td[@onmouseover]")); // almacena todos los dias presentes del calendario
        // ciclo para recorrer la lista de dias del calendario
        for(WebElement dia :dias){
            //si el contador currentDay es igual 1 y es el dia seguiente le hace click (solo selecciona los dias habiles)
            if(currentDay==1 && dia.getAttribute("class").equals("rf-cal-c-cnt-overflow rf-cal-c rf-cal-btn") && click == 0)
            {
                //System.out.println("selecciono este día "+ dia.getText());
                dia.click();
                break;
            }
            // si el dia del calendario es igual al dia presente hace el contador currentDay igual a 1.
            if(dia.getAttribute("class").equals("rf-cal-c-cnt-overflow rf-cal-c rf-cal-today rf-cal-btn")){
                currentDay=1;
            }
        }
    }

    public void clickBtnContinue(){
        WebElement continuar = getDriver().findElement(By.name("ActivacionesForm:btnContinuarActivacionVenta"));
        continuar.click();
    }

    public void windowsScrolldown(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,520)");
    }


    public void validateLinesBd() throws SQLException {
        databasePortInActions.cleanLinesMsisdn(dataExcelModels.getMsisdnPort());
        databasePortInActions.cleanLinesMsi(dataExcelModels.getMsiPort());
    }

    public String validateTransctionBd() throws SQLException {
        return databasePortInActions.executePortabilityTransactionStatus(dataExcelModels.getMsisdnPort());
    }

    public void aceptNitBd() throws SQLException {
        databasePortInActions.executePortabilityNip(dataExcelModels.getMsisdnPort());
    }

    public String consultNipBd() throws SQLException {
        return databasePortInActions.executeSelectNip(dataExcelModels.getNip());
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

    public List<String> executePortabilityTransactionBd() throws SQLException {
        return databasePortInActions.executePortabilityTransaction(dataExcelModels.getMsisdnPort());
    }

    public void executeWindowPortabilityBd() throws SQLException {
        databasePortInActions.executeWindowPortability(dataExcelModels.getMsisdnPort());
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
        String plantx = plan.getText().replace("ó","o");
        MatcherAssert.assertThat("la informacion",
                plantx, Matchers.containsString("No se encontr") );
    }

    public void initialRuteRequestPortability(){
        clickPresale();
        cLickNumberPortability();
        clickRequests();
        clickRequestsNip();
        switchToIframe();
    }
    public void makePortabilityRequest(String msisdn){
        getNumero().waitUntilVisible();
        writePhoneNumberForPortability(msisdn);
        clickBtnRequest();
        WebElement soliNip = getDriver().findElement(By.id("frmSlctdPin:j_id42"));
        //MatcherAssert.assertThat("solicitud nip exitosa",
        //        soliNip.getText(), Matchers.containsString("Las solicitudes se procesaron") );

    }

    public void clickPresale(){
        getPreventa().click();
    }

    public void cLickNumberPortability(){
        getPortabilidadNumerica().click();
    }


    public void clickRequests(){
        getSolicitudes().click();
    }

    public void clickRequestsNip(){
        getSolicitudDeNIP().click();
    }


    public void writePhoneNumberForPortability(String msisdn){
        enter(msisdn).into(getNumero());
    }

    public void clickBtnRequest(){
        getBtnSolicitar().click();
    }

    public void portabilityRequestSoapUI() throws SQLException {
        String response = servicesActions.portabilidad(executePortabilityTransactionBd().get(0),
                executePortabilityTransactionBd().get(1),
                executePortabilityTransactionBd().get(2),
                executePortabilityTransactionBd().get(3),
                executePortabilityTransactionBd().get(5),
                "http://10.69.60.105:8080/PortabilidadServiceEAR-HPNPCommunicationsDelegateEJB/NPCRMWSImpl?wsdl");

        MatcherAssert.assertThat("la respuesta del servicio es O",
                servicesActions.extractResponseInformation(response,"return"),Matchers.equalTo("0"));
       }


}

