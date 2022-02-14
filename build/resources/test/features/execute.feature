@test
Feature: Sanity limpieza y aprovisionamiento de recursos

  yo como que quiero realizar una limpieza de recursos
  cargar la mercancia y el inventario en la plataforma epos

  @epos
  Scenario: se quiere ingresar a la plataforma epos
    Given Se ejecutan procedimientos en bd y soapUi
    When Se ingresa a la plataforma epos para cargue de inventario
      | user       | password   |
      | CQ10960370 | Tigo.2022* |
    And Se ingresa a entrada masiva de mercancia
    Then Se completa datos para cargar mercancia

  @inventory
  Scenario: realizar el cargue de inventario
    Given Se ingresa a la plataforma epos para cargue de inventario
      | user       | password   |
      | CQ10960370 | Tigo.2022* |
    When  Se ingresa a cargue de inventario
    Then Deberia poder realizar el cargue de inventario

  @windex
  Scenario: realizar la confirmacion de inventario
    Given se ingresa a la plataforma epos windex
      | user       | password   |rutaWinWap|
      | CQ10960370 | Tigo.2022* |C:\Program Files (x86)\Winwap Technologies\WinWAP for Windows 4.2\WinWAP4.exe|
    Then se deberia poder ver mensaje de confimacion exitosa

  @portalPrepaid
  Scenario: se requiere realizar una activacion de una linea en prepago
    Given Se ingresa al portal CRM para activacion
      | user       | password   |
      | CQ10960370 | Tigo.2022* |
    When Se hace activacion de una linea en prepago
    Then Se deberia ver en pantalla unica la linea activa en prepago


  @portalControl
  Scenario: se requiere realizar una activacion de una linea en control
    Given Se ingresa al portal CRM para activacion
      | user       | password   |
      | CQ10960370 | Tigo.2022* |
    When Se hace activacion de una linea en control
    Then Se deberia ver en pantalla unica la linea activa en control

  @portalCesion
  Scenario: se requiere realizar una activacion de una linea en prepago
    Given Se ingresa al portal CRM para activacion
      | user       | password   |
      | CQ10960370 | Tigo.2022* |
    When Se hace la cesion de contrato de una linea
    Then Se deberia ver en pantalla unica la linea cedida


