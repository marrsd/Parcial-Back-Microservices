## Circuit Breaker

Implementamos el patron circuit breaker dentro del microservicio catalog que tienen una comunicación con los servicios Movies y Series.

Por ejemplo, Al momento de querer guardar una película o una serie, entra una solicitud por el gateway y pasa al microservicio de catalog que usa ya sea la API expuesta por el microservicio Movie o por la API del microservicio Serie para poder registrar el nuevo dato. Si el microservicio Movie o Serie se encuentran temporalmente no disponibles, ya sea por fallos de red, tráfico elevado, no se pueda conectar a la base de datos, u otra razón, y realizamos varios  intentos y estos superan el 50% como límite de falla de 5 eventos predefinidos para analizar o 5 llamadas hechas, entonces el circuit breaker pasa a un estado abierto, esto permite que no siga enviándose solicitudes al servicio remoto. Luego espera 1.5 segundos antes de pasar a un estado semi abierto en donde se permite enviar un numero limitado de solicitudes, en nuestro caso 3 intentos, que si fallan vuelve nuevamente a un estado abierto. 
Internamente se realizan dos reintentos, en un perido de 1 segundo, y en caso de que no haya respuesta exitosa, se ejecuta el método alternativo. 
Con esto evitamos que hayan fallos en cascada y por tanto que la aplicación se caiga y así permitir que otras partes de la app sigan funcionando.

![Esquema circuit breaker](https://github.com/marrsd/Parcial-Back-Microservices/blob/develop/screenshots/Esquema_circuit_breaker.jpeg)