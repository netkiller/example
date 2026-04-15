# Microservice
Spring Cloud Exampe

![Java CI with Maven](https://github.com/netkiller/Microservice/workflows/Java%20CI%20with%20Maven/badge.svg)

# Config

	iMac:Java neo$ curl http://localhost:8888/config-client-dev.json
	{"server":{"port":8762},"foo":"foo version 1"}
	
	iMac:resources neo$ curl -k https://config:s3cr3t@localhost:8888/netkiller-dev.json
	{"sms":{"gateway":{"url":"https://sms.netkiller.cn/v1","username":"netkiller","password":"123456"}}}
	
	