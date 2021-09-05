start-containers:
	docker-compose -f ./docker/docker-compose.yaml up -d

stop-containers:
	docker-compose -f ./docker/docker-compose.yaml down

start-application:
	mvn spring-boot:run

start-containers-application:
	make start-containers
	make start-application

list-containers:
	docker-compose -f ./docker/docker-compose.yaml ps