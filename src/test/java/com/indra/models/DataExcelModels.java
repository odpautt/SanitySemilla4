package com.indra.models;

import com.indra.actions.ReadFileXLSXActions;

public class DataExcelModels {
    String urlEpos;
    String urlCRM;
    String urlComfirmador;
    String urlGatewayCBS;
    String urlGatewayMG;
    String MSISDN;
    String Plu;
    String Serial;
    String urlDBA;
    String serviceA;
    String userA;
    String passwordA;
    String urlDBE;
    String serviceE;
    String userE;
    String passwordE;
    String urlDBS;
    String serviceS;
    String userS;
    String passwordS;
    String urlDBP;
    String serviceP;
    String userP;
    String passwordP;
    String port;

    String vendedorPrepago;
    String cedulaClientePrepago;
    String msisdnPrepago;
    String msiPrepago;

    String VendedorPostpago;
    String CedulaClientePostpago;
    String msisdnPostpago;
    String msiPostpago;

    public String getVendedorPrepago() {
        fileXLSX.readFileExcel();
        vendedorPrepago = fileXLSX.excelArray.get(10).get(0);
        return vendedorPrepago;
    }

    public String getCedulaClientePrepago() {
        fileXLSX.readFileExcel();
        cedulaClientePrepago = fileXLSX.excelArray.get(10).get(1);
        return cedulaClientePrepago;
    }

    public String getMsisdnPrepago() {
        fileXLSX.readFileExcel();
        msisdnPrepago = fileXLSX.excelArray.get(10).get(2);
        return msisdnPrepago;
    }

    public String getMsiPrepago() {
        fileXLSX.readFileExcel();
        msiPrepago = fileXLSX.excelArray.get(10).get(3);
        return msiPrepago;
    }

    public String getVendedorPostpago() {
        fileXLSX.readFileExcel();
        VendedorPostpago = fileXLSX.excelArray.get(11).get(0);
        return VendedorPostpago;
    }

    public String getCedulaClientePostpago() {
        fileXLSX.readFileExcel();
        CedulaClientePostpago = fileXLSX.excelArray.get(11).get(1);
        return CedulaClientePostpago;
    }

    public String getMsisdnPostpago() {
        fileXLSX.readFileExcel();
        msisdnPostpago = fileXLSX.excelArray.get(11).get(2);
        return msisdnPostpago;
    }

    public String getMsiPostpago() {
        fileXLSX.readFileExcel();
        msiPostpago = fileXLSX.excelArray.get(11).get(3);
        return msiPostpago;
    }

    ReadFileXLSXActions fileXLSX = new ReadFileXLSXActions();

    public String getUrlEpos() {
        return urlEpos;
    }

    public String getUrlCRM() {
        fileXLSX.readFileExcel();
        urlCRM = fileXLSX.excelArray.get(1).get(1);
        return urlCRM;
    }

    public String getUrlComfirmador() {
        return urlComfirmador;
    }

    public String getUrlGatewayCBS() {
        fileXLSX.readFileExcel();
        urlGatewayCBS = fileXLSX.excelArray.get(1).get(3);
        return urlGatewayCBS;
    }

    public String getUrlGatewayMG() {
        fileXLSX.readFileExcel();
        urlGatewayMG = fileXLSX.excelArray.get(1).get(4);
        return urlGatewayMG;
    }

    public String getMSISDN() {
        fileXLSX.readFileExcel();
        MSISDN = fileXLSX.excelArray.get(10).get(2);
        return MSISDN;
    }

    public String getPlu() {
        return Plu;
    }

    public String getSerial() {
        return Serial;
    }

    public ReadFileXLSXActions getFileXLSX() {
        return fileXLSX;
    }

    public String getUrlDBA() {
        fileXLSX.readFileExcel();
        urlDBA = fileXLSX.excelArray.get(3).get(0);
        return urlDBA;
    }

    public String getServiceA() {
        fileXLSX.readFileExcel();
        serviceA = fileXLSX.excelArray.get(3).get(1);
        return serviceA;
    }

    public String getUserA() {
        fileXLSX.readFileExcel();
        userA = fileXLSX.excelArray.get(3).get(2);
        return userA;
    }

    public String getPasswordA() {
        fileXLSX.readFileExcel();
        passwordA = fileXLSX.excelArray.get(3).get(3);
        return passwordA;
    }

    public String getUrlDBE() {
        fileXLSX.readFileExcel();
        urlDBE = fileXLSX.excelArray.get(4).get(0);
        return urlDBE;
    }

    public String getServiceE() {
        fileXLSX.readFileExcel();
        serviceE = fileXLSX.excelArray.get(4).get(1);
        return serviceE;
    }

    public String getUserE() {
        fileXLSX.readFileExcel();
        userE = fileXLSX.excelArray.get(4).get(2);
        return userE;
    }

    public String getPasswordE() {
        fileXLSX.readFileExcel();
        passwordE = fileXLSX.excelArray.get(4).get(3);
        return passwordE;
    }

    public String getUrlDBS() {
        fileXLSX.readFileExcel();
        urlDBS = fileXLSX.excelArray.get(6).get(0);
        return urlDBS;
    }

    public String getServiceS() {
        fileXLSX.readFileExcel();
        serviceS = fileXLSX.excelArray.get(6).get(1);
        return serviceS;
    }

    public String getUserS() {
        fileXLSX.readFileExcel();
        userS = fileXLSX.excelArray.get(6).get(2);
        return userS;
    }

    public String getPasswordS() {
        fileXLSX.readFileExcel();
        passwordS = fileXLSX.excelArray.get(6).get(3);
        return passwordS;
    }

    public String getUrlDBP() {
        fileXLSX.readFileExcel();
        urlDBP = fileXLSX.excelArray.get(5).get(0);
        return urlDBP;
    }

    public String getServiceP() {
        fileXLSX.readFileExcel();
        serviceP = fileXLSX.excelArray.get(5).get(1);
        return serviceP;
    }

    public String getUserP() {
        fileXLSX.readFileExcel();
        userP = fileXLSX.excelArray.get(5).get(2);
        return userP;
    }

    public String getPasswordP() {
        fileXLSX.readFileExcel();
        passwordP = fileXLSX.excelArray.get(5).get(3);
        return passwordP;
    }

    public String getPort() {
        port = "1521";
        return port;
    }
}
