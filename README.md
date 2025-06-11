# Books-Catalogue Microservice
Aplicación que se encarga de ejecutar la acción principal (CRUD) de libros en la aplicación relatos de papel

Las operaciones que la API debe soportar son las siguientes:
- Crear libros
- Consultar lista de libros
- Consultar libros por id
- Consultar libros consulta combinada (por título, autor, categoria, precio, calificación, isbn, visible, stock, fecha de publicación)
- Actualizar libros completamente
- Actualizar libros parcialmente
- Eliminar libros

**Recursos identificados**
- books (Libros): Representa un libro en el sistema.

| Método Http | Endpoint    | Query Params | Cuerpo JSON de la petición           | Respuesta JSON de la petición                                                                                                                                           | Códigos HTTP de respuesta posibles                               |
|-------------|-------------|--------------|--------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| POST        | /books      |              | `{"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","precio":0,"calificacion":0,"visible":true,"stock":0}`  | `{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}` | 201 Created, 400 Bad Request, 500 Internal Server Error          |
| GET         | /books      |              |                                      | `[{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}]`| 200 OK, 500 Internal Server Error, 400 Bad Request, 404 Not Found|
| GET         | /books/{id} |              |                                      | `{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}`| 200 OK, 404 Not Found, 500 Internal Server Error,404 Not Found   |
| GET         | /books      | `titulo`, `autor`, `categoria`, `precio`, `calificacion`, `isbn`, `visible`, `stock`, `fechapublicacion` |                                      | `[{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}]`| 200 OK, 500 Internal Server Error, 400 Bad Request,404 Not Found |
| PUT         | /books/{id} |              | `{"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","precio":0,"calificacion":0,"visible":true,"stock":0}`  | `{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}` | 200 OK, 400 Bad Request, 404 Not Found, 500 Internal Server Error |
| PATCH       | /books/{id} |              | `{"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","precio":0,"calificacion":0,"visible":true,"stock":0}`  | `{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}` | 200 OK, 400 Bad Request, 404 Not Found, 500 Internal Server Error |
| DELETE      | /books/{id} |              |                                      | `{"id":0,"titulo":"string","autor":"string","fechapublicacion":"2025-06-11","categoria":"string","isbn":"string","calificacion":0,"visible":true,"stock":0,"precio":0}`| 200 OK, 404 Not Found, 500 Internal Server Error, 400 Bad Request|