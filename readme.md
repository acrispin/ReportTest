Proyecto test de reportes jasper a pdf

Se usa las librerias jasper 5.6.0
https://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.6.0/

Si se presenta el error en ubuntu: 
net.sf.jasperreports.engine.util.JRFontNotFoundException: Font 'Times New Roman' is not available to the JVM

Solucion:
https://stackoverflow.com/questions/3811908/font-is-not-available-to-the-jvm-with-jasper-reports

Instalar el paquete:
$ sudo apt-get install ttf-mscorefonts-installer
