CREATE DATABASE IF NOT EXISTS infix_ads_db;
USE infix_ads_db;
DROP PROCEDURE IF EXISTS init;
DELIMITER //
CREATE PROCEDURE init ()
LANGUAGE SQL
BEGIN
  DECLARE user_exist, data_present INT;
  SET user_exist = (SELECT EXISTS (SELECT DISTINCT user FROM mysql.user WHERE user = "root"));
  IF user_exist = 0 THEN
    CREATE USER 'root'@'localhost' IDENTIFIED BY '';
    GRANT ALL PRIVILEGES ON infix_ads_db.* TO 'root'@'localhost';
    FLUSH PRIVILEGES;
  END IF;
  CREATE TABLE IF NOT EXISTS infix_ads (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64),
    url VARCHAR(200),
    image_url VARCHAR(200),
    priority INT
  );
  SET data_present = (SELECT COUNT(*) FROM infix_ads);
  IF data_present = 0 THEN
    INSERT INTO infix_ads (name, url, image_url, priority) VALUES
      ('RocketWeb', 'https://codecanyon.net/item/rocketweb-android-web-app-solution-webtoapp/22985174', 'https://i.imgur.com/mi04xkS.jpg', 100),
      ('ESC/POS Bluetooth Print', 'https://play.google.com/store/apps/details?id=infixsoft.imrankst1221.printer', 'https://i.imgur.com/lCVsAGd.png', 100),
      ('Student AIVUS', 'https://play.google.com/store/apps/details?id=com.imrankst1221.example.aivus', 'https://i.imgur.com/DaRU1Ry.png', 100);

  END IF;
END;//
DELIMITER ;
CALL init();