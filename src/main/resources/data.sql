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