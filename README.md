# quotation-management


Para Inicializar a Aplicação:

*docker network create inatel

*docker container run --name mysql --network=inatel -e
MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306
-p 33060:33060 -d mysql

*docker container run --name stockmanager --network=inatel -p
8080:8080 -d adautomendes/stock-manager

*cd QuatationManagement

*docker image build -t inatel .

*docker run -p 8081:8081 inatel --network=inatel




PS*(EU PODERIA TER CRIADO UM DOCKER-COMPOSE PARA GERENCIAR ESSAS IMAGENS, MAS NÃO DEU TEMPO)
