# brokerage-app

------------------------------------------------------------------------------------------

-- HOW TO RUN

Spring active profile is set to prod. To change it you should edit application.yaml in resources folder.

There are 3 ways to run this application;

1) Maven Command 
mvn spring-boot:run

2) Java Command
./mvnw clean install
cd target
java -jar .\brokerage-1.0.0.jar

3) Docker Container
mvn compile jib:dockerBuild
docker run -p 80:80 brokerage:latest

------------------------------------------------------------------------------------------

-- H2 Database

http://localhost/h2-console

JDBC URL;
dev : jdbc:h2:mem:dev
test: jdbc:h2:mem:test
prod: jdbc:h2:mem:prod

------------------------------------------------------------------------------------------

Endpoints can be seen with swagger. 

http://localhost/swagger-ui/index.html

Postman collection is provided as a json file. (brokerage_postman_collection.json)

3 different user logins are provided with collection.

After successful login response returns Bearer JWT token as Authorization key in headers.

You should set this JWT token in every request header as Authorization key.

------------------------------------------------------------------------------------------

A unit test is provided in tests folder as AssetUtilTests

------------------------------------------------------------------------------------------

Initial H2 database customers and assets data is;

INSERT INTO `customers` (`customer_id`,`customer_name`, `email`, `encrypted_password`) VALUES (1, 'Onur', 'onur@gmail.com', '{bcrypt}$2a$10$/mw6kw0p1SvKKfP3OdK8LOacaHG9CoPw/oLjUz8ubXMSt8Wifi/Ei');
INSERT INTO `customers` (`customer_id`,`customer_name`, `email`, `encrypted_password`) VALUES (2, 'Merve', 'merve@gmail.com', '{bcrypt}$2a$10$/mw6kw0p1SvKKfP3OdK8LOacaHG9CoPw/oLjUz8ubXMSt8Wifi/Ei');
INSERT INTO `customers` (`customer_id`,`customer_name`, `email`, `encrypted_password`) VALUES (3, 'Ali', 'ali@gmail.com', '{bcrypt}$2a$10$/mw6kw0p1SvKKfP3OdK8LOacaHG9CoPw/oLjUz8ubXMSt8Wifi/Ei');

INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (1, 1, 'TRY', 800, 800, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (2, 1, 'USD', 100, 100, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (3, 1, 'EUR', 200, 200, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (4, 1, 'XAU', 150, 150, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (11, 2, 'TRY', 800, 800, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (12, 2, 'USD', 100, 100, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (13, 2, 'EUR', 200, 200, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (14, 2, 'XAU', 150, 150, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (21, 3, 'TRY', 800, 800, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (22, 3, 'USD', 100, 100, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (23, 3, 'EUR', 200, 200, 1);
INSERT INTO `assets` (`asset_id`,`customer_id`, `asset_name`, `size`, `usable_size`, `version`) VALUES (24, 3, 'XAU', 150, 150, 1);

------------------------------------------------------------------------------------------