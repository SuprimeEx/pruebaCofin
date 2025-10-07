1) HashMap y ConcurrentHashMap son similares ya que ambos almacenan pares de clave y valor, pero HashMap no funciona correctamente si varios hilos lo utilizan al mismo tiempo, mientras que ConcurrentHashMap lo hace bien porque está diseñado para manejar múltiples hilos sin problemas.
List, Set y Map son colecciones diferentes: la lista (List) mantiene los elementos en un orden específico y permite duplicados, el conjunto (Set) no admite elementos repetidos y no garantiza un orden particular, y el mapa (Map) almacena claves junto con sus valores, donde cada clave es única.

2) Optional es similar a un recipiente que puede contener un valor o no. Su función es prevenir el uso de null y reducir errores, obligándonos a verificar si hay un valor antes de utilizarlo.

3) Para manejar excepciones en un servicio que toca base de datos, lo normal es atrapar las excepciones que puedan salir, registrar lo que pasó, cerrar conexiones bien y si hace falta lanzar una excepción propia para que la parte que llama sepa que algo falló.

4) Las tres son anotaciones que le dicen a Spring "esta clase hace algo". Pero cada una tiene su propósito:

      @Component: Es la más genérica. se usa cuando se tiene una clase que hace algo útil pero no encaja claramente en las otras categorías. Por ejemplo, un validador, un conversor, o cualquier utilidad.

      @Service: Se pone en clases que tienen lógica de negocio. Aquí van tus reglas de negocio, cálculos, validaciones complejas, orquestación de operaciones
   
      @Repository: Se para el acceso a datos, además convierte excepciones de la base de datos en excepciones manejables por Spring (DataAccessException).

5) En Fineract, los endpoints de la API REST se crean usando controladores. Básicamente, un controlador es una clase que recibe las solicitudes que vienen de afuera y decide qué hacer con ellas. Cada método de esa clase representa un endpoint, o sea, una acción concreta que se puede pedir, como obtener datos, crear algo nuevo o actualizar información. La información que envía quien hace la solicitud puede venir en la dirección web o dentro del cuerpo del mensaje. Así, los controladores son los que conectan lo que hace el sistema por dentro con lo que los usuarios pueden pedir desde afuera.
