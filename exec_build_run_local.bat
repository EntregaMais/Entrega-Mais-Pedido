@echo off 
Rem  Roda a aplicacao localmente
mvn clean && docker compose up -d postgres redis redis-commander && mvn spring-boot:run