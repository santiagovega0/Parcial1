Guía de ejecución del proyecto:

1.    Descargar el repositorio en formato zip

2.    Configuración del entorno:
        Tener Java y Gradle instalados.
        Configurar las propiedades de la base de datos H2 en el archivo application.properties.

3.    Ejecución del proyecto de manera local:
        Una vez configurado el proyecto se ejecuta la aplicación main.

Tests:
    Para ejecutar los tests y comprobar el code coverage, se ejecuta PersonaServiceTest

Acceso a los servicios:
    Con un servicio cómo Postman puede interactuar con la API mientras se corre la aplicación usando la siguiente URL: http://localhost:8080 y acceder a los servicios posibles:
        Detección de mutantes: POST /mutant/
        Enviar un array de strings simulando una matriz cuadrada. Las letras de los Strings solo pueden ser: (A,T,C,G)
        Estadísticas: GET /stats

    Tambien se puede utilizar el endpoint con la siguiente url: https://apimutantes2.onrender.com
    La ruta /stats muestra la cantidad de mutantes y no mutantes.
    La ruta /mutant recibe un objeto que contiene un array de strings y devuelve si es o no mutante.
