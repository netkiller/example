# Readme

	mvn package
	mvn docker:build
	mvn docker:push
	
	iMac:welcome neo$ docker images welcome
	REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
	iMac:welcome neo$ docker images | grep welcome
	127.0.0.1:5000/netkiller/welcome                  latest              0fc7c7625e65        3 minutes ago       546MB
	
# Kubernetes



	minikube start --memory 2048mb --cpus 2 \
	--cache-images=true \
	--driver=hyperkit \
	--image-mirror-country=cn \
	--insecure-registry="192.168.3.85:5000" \
	--registry-mirror="https://registry.docker-cn.com,https://docker.mirrors.ustc.edu.cn" \
	--service-cluster-ip-range='10.10.0.0/24'
	
	kubectl create deployment welcome --image=127.0.0.1:5000/netkiller/welcome:latest
	
	kubectl create deployment welcome --image=192.168.3.85:5000/netkiller/welcome:latest
	kubectl expose deployment welcome --port=8080 --target-port=8080 --type=NodePort