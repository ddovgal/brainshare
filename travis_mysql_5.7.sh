#!/usr/bin/env bash
wget http://dev.mysql.com/get/mysql-apt-config_0.8.5-1_all.deb
sudo dpkg -i mysql-apt-config_0.8.5-1_all.deb
sudo apt-get update
sudo apt-get install mysql-server
sudo mysql_upgrade