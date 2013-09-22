wsimport -s src/main/java -p de.bio.hazard.securemessage.webservice.message http://localhost:8080/messageWebservice?wsdl
wsimport -s src/main/java -p de.bio.hazard.securemessage.webservice.authentication http://localhost:8080/authenticationWebservice?wsdl
wsimport -s src/main/java -p de.bio.hazard.securemessage.webservice.basisinfo http://localhost:8080/basisInfoWebservice?wsdl
wsimport -s src/main/java -p de.bio.hazard.securemessage.webservice.user http://localhost:8080/userWebservice?wsdl