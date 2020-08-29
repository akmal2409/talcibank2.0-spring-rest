# talcibank2.0-spring-rest

## To generate RSA key open your terminal and paste two commands
    keytool -genkey -alias talcibank -keyalg RSA -keystore talcibank.jks -keysize 2048
    
    keytool -importkeystore -srckeystore talcibank.jks -destkeystore talcibank.jks -deststoretype pkcs12
