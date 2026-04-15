First we must generate a KeyStore file. 
The command will generate a file called jwt.jks which contains the Public and Private keys.

keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password

It is recommended to migrate to PKCS12. To do that execute the following command:

keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12

Now let's export the public key:

keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey