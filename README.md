## Circuit Breaker

Implementamos el patron circuit breaker dentro del microservicio catalog que tienen una comunicación con los servicios Movies y Series.

Por ejemplo, Al momento de querer guardar una película o una serie, entra una solicitud por el gateway y pasa al microservicio de catalog que usa ya sea la API expuesta por el microservicio Movie o por la API del microservicio Serie para poder registrar el nuevo dato. Si el microservicio Movie o Serie se encuentran temporalmente no disponibles, ya sea por fallos de red, tráfico elevado, no se pueda conectar a la base de datos, u otra razón, y realizamos varios  intentos y estos superan el 50% como límite de falla de 5 eventos predefinidos para analizar o 5 llamadas hechas, entonces el circuit breaker pasa a un estado abierto, esto permite que no siga enviándose solicitudes al servicio remoto. Luego espera 1.5 segundos antes de pasar a un estado semi abierto en donde se permite enviar un numero limitado de solicitudes, en nuestro caso 3 intentos, que si fallan vuelve nuevamente a un estado abierto. 
Internamente se realizan dos reintentos, en un perido de 1 segundo, y en caso de que no haya respuesta exitosa, se ejecuta el método alternativo. 
Con esto evitamos que hayan fallos en cascada y por tanto que la aplicación se caiga y así permitir que otras partes de la app sigan funcionando.

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Esquema_circuit_breaker.jpeg" alt="Esquema circuit breaker">
</p>

## Evidencias

Se realizan solicitudes desde postman para observar las rutas y la interacción entre servicios.

Solicitud guardar movie:

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Save_movie_Postman.jpeg">
</p>

La solicitud primero llega a catalog-service, que luego la enruta a movie-service. movie-service interactúa con rabbitmq y mongodb-catalog para completar la operación de guardar una película.
El servicio que más tiempo toma en procesar la solicitud es catalog-service.

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Zipkin_post_catalog_movie_save1.jpeg">
</p>

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Zipkin_post_catalog_movie_save2.jpeg">
</p>

La solicitud de guardar serie pasa por catalog-service y luego se enruta a serie-service. Este último interactúa con rabbitmq para que el mensaje sea guardado en mongodb-catalog, igualmente también es guardado en la base de datos de serie, mongodb-test para completar la operación de guardar una serie.

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Zipkin_post_catalog_serie_save1.jpeg">
</p>

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Zipkin_post_catalog_serie_save2.jpeg">
</p>

En el caso de la solicitud de listar por género, este pasa por la base de datos de catalog, buscando las series y películas que cumplan la condición, está traza es más corta ya que realiza la búsqueda sobre la misma base de datos de catalog.

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Zipkin_get_catalog_byGenre1.jpeg">
</p>

<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Zipkin_get_catalog_byGenre%202.jpeg">
</p>

Evidencias de los mensajes enviados a Rabbitmq.

Mensaje en cola del servicio movie.
<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/cola_movie.jpeg">
</p>

Mensaje en cola del servicio serie.
<p align="center">
  <img src="https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/cola_serie.jpeg">
</p>
