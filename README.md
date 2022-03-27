# Logistics
API REST Prueba Tecnica

servicio web desarrollado con las siguientes tecnologias:
Java 8
springframework 2.6.3 (Spring Boot)
Base de datos: PostgreSQL
JWT (jsonwebtoken) para la autenticacion
Postman para Pruebas Unitarias


## listado de Endpoints
Seguridad:
https://pruebalogistica1.herokuapp.com/api/auth/signup
https://pruebalogistica1.herokuapp.com/api/auth/login
Crud:
Method POST - https://pruebalogistica1.herokuapp.com/admin/product/
Method GET https://pruebalogistica1.herokuapp.com/admin/product/1
Method PUT - https://pruebalogistica1.herokuapp.com/admin/product/1
Method DELETE - https://pruebalogistica1.herokuapp.com/admin/product/1

Other  (se incluyen los metodos necesarios para prueba)
https://pruebalogistica1.herokuapp.com/admin/customer/
https://pruebalogistica1.herokuapp.com/crud/warehouse/
http://pruebalogistica1.herokuapp.com/admin/service/
https://pruebalogistica1.herokuapp.com/deliveries/
https://pruebalogistica1.herokuapp.com/admin/customer/1
https://pruebalogistica1.herokuapp.com/admin/customer/all

Envios
https://pruebalogistica1.herokuapp.com/deliveries/allByClient/1


se incluyen solo CRUD de Productos como ejemplo, los demas enpoint para las demas entidades son similares


Repositorio
el repositorio esta alojado en GitHub
url : https://github.com/RicardoFlamenco/Logistics/tree/main


Deploy
el despliegue se hizo utilizando Heroku como herramienta y se conecto con GitHub para los despliegeues automaticos
url : https://pruebalogistica1.herokuapp.com/

los detalles de desarrollo de esta prueba se definen en el documento correspondiente que se adjunta a dicha prueba