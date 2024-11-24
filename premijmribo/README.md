# Pokédex App - Proyecto de Hackathon

David Berrocal Fidalgo - Sergi Sanmartin Soria - Ivan Moreno Santin

## Introducción
Este proyecto es el resultado del desafío propuesto por GFT durante la hackathon. Nuestro objetivo era desarrollar una aplicación de Pokédex interactiva que permitiera a los usuarios consultar información sobre los Pokémon, capturarlos, guardar zonas y participar en torneos.

---

## Contenido del Directorio
Este directorio (`premijmribo`) contiene las siguientes evidencias del proyecto:

1. **README.md**: Este documento con información detallada del proyecto.
2. **Diagramas y diseños**:
   - Diagramas UML del sistema.
   - Diseños de la interfaz gráfica de usuario (mockups o capturas).
3. **Enlaces al código**: Referencias al código fuente del proyecto dentro del repositorio.

---

## Diagramas

### Diagrama UML

![Diagrama UML](/UML.jpg)

### Diagrama Secuencial

![Diagrama Secuencial 1](/DiagramaSequencia1.jpg)
![Diagrama Secuencial 2](/DiagramaSequencia2.jpg)

### Diagrama de casos de uso

![Diagrama de casos de uso](/casosDus.jpg)

### Mockups de la Interfaz
*(Incluye imágenes de los diseños de la interfaz gráfica, como las pantallas iniciales, las funcionalidades de la Pokédex, etc.)*

![Mockup Pantallas 1](/dissenyUI.jpg)
![Mockup Pantallas 2](/dissenyUI2.jpg)

### Aspectos generales

![Aspectos generales](/aspectesgenerals.jpg)

### Algoritmo genético

![Disseño algoritmo genetico](/algGenetico.jpg)

### Diseño factoría

[!Diseño factoria](/factoriaAdaptadors.jpg)

---

## Buenas prácticas realizadas

- **Patrón MVVM**: Implementación del patrón MVVM para mantener una clara separación entre la interfaz de usuario y la lógica de negocio.
- **Uso de Jetpack Compose**: Nos permitió crear una interfaz moderna y reactiva.
- **Código modular**: División del proyecto en módulos lógicos y reutilizables.
- **Trabajo colaborativo**: Utilización de Git y GitHub para la coordinación y gestión del trabajo en equipo.
- **Patrón Estrategia**: Uso del patrón Estrategia para fácil ampliación del algoritmo usado, y permitir cambio en tiempo de ejecución.
- **Patrón Factoria Adaptador**: Desacoblamos las APIS del sisitema con adptadores, para fácil cambio en cualquier caso, y delegamos creación de adaptadores a factoria.
- **Patrón ServiceLoctor y DTOS**: Así podremos minimizar daños en nuestra APP en caso de que la API falle o cambie.
- **Uso de Movil Para Tests**: Hemos hecho muchos casos de prueba en móvil para garantizar su correcto funcionamiento.
- **Nombre  adhiente en las clases**: De esta forma facilitamos la comprensión del código.
- **Metodolgia Agile**: Nos basamos en la metodolgia Agile para poder trabajr en equipo, para tener feedback immediato, poder responder a requisistos cambiantes, y poder hacer una implementación iterativa y estructurada. La cual cosa nos sirvió muchísimo.

---

## Aspectos mejorables

- **Optimización del rendimiento**: Reducir los tiempos de carga al consultar datos desde la API.
- **Interfaz de usuario más pulida**: Mejorar animaciones y añadir efectos visuales para hacer la experiencia más atractiva.
- **Funcionalidades adicionales**: Ampliar las características de los torneos y añadir más opciones de personalización para los equipos Pokémon.
- **Funcionalidades de IA adicionales**: Ampliar las funcionalidades mediante IA.
- **Uso de Git**: Podriamos haber hecho un uso más correcto del Git para facilitar el desarrollo.
- **Recuperación de datos en caso de fallo**: Nos hubiera gustado poder implementar una Base de Datos local en el dispositivo mòvil, a si, en caso de fallo de las APIs podríamos seguir operando con la información guardada en local, aun i así se nos fue del alcance, y n pudimos conseguirlo

---

## Conclusiones

Aunque no hemos podido implementar tantas funcionalidades innovadoras como nos hubiera gustado debido a la falta de experiéncia y a otros problemas que siempre surgen en competiciones así, podemos decir que estamos muy orgullosos del proyecto entregado ya que tenemos una buena estructura bien defindia, un codigo robusto y claro. Además la hemos diseñado de manera que sea mantenible y al miesmo tiempo sea fácil de expandir sus funcionalidades sin compremeter el resto del código.

---

## Enlaces al código

- [Código fuente completo de la aplicación](../app)
- [Archivos automatización Python](../PythonPokemonAuto)

---

¡Gracias por vuestro tiempo y valoración!
