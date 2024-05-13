# SkyLink
## Integrantes:
- Aramayo Gonzales Tatiana Jael.
- Camargo Orozco Luciana Fernanda.
- Quisbert Moya Patricia Andrea.
- Sonco Villegas Melany Andrea.
- Vacaflor Nina Leonardo Emanuel.
## ¿De qué se trata el proyecto? 
Hoy en día, una de las principales problemáticas que existen dentro de la ciudad de La Paz es hallar un modo de movilizarse a lo largo de toda la urbe paceña. Cada persona tiene el tiempo contado y, por estadística, tenemos que la mayoría de la población no cuenta con un automóvil propio para poder movilizarse, tanto dentro como fuera de la ciudad, por lo que se implementaron nuevos tipos de transporte acorde a las distintas necesidades de la población, habilitando con ello el servicio de transporte público "Mi Teleférico".

Este resultó ser uno de los mejores transportes para el momento, novedoso y rápido, que era lo que a la gente le angustiaba. 

Contrario a lo que se esperaría de un nuevo e innovador tipo de transporte, este paso rápidamente a ser uno más del montón, así como el PumaKatari u otros. Se empezó a hacer una especie de comparación entre cada uno de los transportes disponibles para poder elegir el más adecuado. Con esa idea en mente es que decidimos crear "SkyLink", una aplicación que considera tanto el tiempo que se emplea en llegar de un destino a otro como el precio a pagar por el servicio. 

Con SkyLink buscamos encontrar el camino más cómodo posible para poder transportarse en el teleférico en cualquiera de las líneas habilitadas, esto de una manera rápida y sencilla para que cada usuario pueda comparar sus opciones en un instante. Con esto buscamos tener en cuenta la comodidad del pasajero tanto en cuestión de tiempo como en cuestión de dinero, buscando la ruta menos compleja para que el usuario pueda llegar a su destino de la mejor manera posible.

<div id="lineas">
  <ul align="center">
    <img height="400" width="500" alt="PNG" src="https://www.miteleferico.bo/_nuxt/img/mapa_mt_nuevo.6f8194e.png">
    </ul>
</div>

## Instalación del Programa
Para instalar el programa adecuadamente se deben seguir los siguiente pasos:

####  <summary><h2 style="display: inline-block"><img src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/217d5ea0-623d-40b1-9b31-027b904a5f15/ddjrgww-846ce429-3b0d-4ad8-bf6d-ac52dfe48201.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzIxN2Q1ZWEwLTYyM2QtNDBiMS05YjMxLTAyN2I5MDRhNWYxNVwvZGRqcmd3dy04NDZjZTQyOS0zYjBkLTRhZDgtYmY2ZC1hYzUyZGZlNDgyMDEucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.G0SE64OMLNEGI8vXb21JRl13RMfER1VP8Kh2Ig3oJaQ" width=25px>  Instalar Visual Studio Code</h2></summary>
Visual Studio Code es un editor de código fuente que fue desarrollado por Microsoft. Es gratuito y de código abierto, además de multiplataforma, por lo que está disponible para Windows, macOs y Linux.

Instalaremos esta aplicación para ejectuar el programa propuesto en este repositorio.

Para instalar Visual Studio Code puede ver el siguiente [video guía](https://youtu.be/X_Z7d04x9-E?si=_RdSWXTya-nPbU4I) o dirigirse a la [Página oficial de Visual Studio Code](https://code.visualstudio.com/).

####  <summary><h2 style="display: inline-block"><img src="https://cdn.iconscout.com/icon/free/png-256/free-java-23-225999.png" width=25px>  Instalar el compilador de Java </h2></summary>  
El programa propuesto se encuentra escrito en Java, por lo que será necesario tener instalado un compilador de Java para poder ejecutar el código desde su ordenador. Para esto puede seguir el siguiente [video guía](https://www.youtube.com/watch?v=5voE8tvtVV8)

####  <summary><h2 style="display: inline-block"><img src="https://w7.pngwing.com/pngs/293/108/png-transparent-computer-icons-document-management-system-document-file-format-directory-others-miscellaneous-purple-text.png" width=25px>  Descargar los archivos del proyecto </h2></summary> 
Para finalizar, debemos descargar los archivos de este repositorio a su ordenador. Esto puede hacerse de manera manual, descargando los archivos uno por uno y colocandolos en una carpeta que luego se abrirá con Visual Studio Code, o incluso copiando el texto que se encuentra dentro de los archivos y pasarlos a archivos creados desde su computadora. Pero la manera más sencilla es haciendo uso de Git. Para esto, puede instalarlo desde la [Página oficial de Git](https://git-scm.com/).

Una vez haya instalado correctamente Git abra la terminal de comandos, dirijase a una carpeta de su preferencia (donde guardará el programa) y ejecute los siguientes comandos:
```
git init
git clone https://github.com/LeonardoVNC/SkyLink.git
```

Esto clonará todos los archivos y ramas del repositorio en su ordenador. Basta con arrastrar esta carpeta a Visual Studio Code para añadirla a su Espacio de Trabajo. Una vez ahi, abra todos los archivos para comprobar que se hayan descargado correctamente. 
## ¿Como ejecutamos el programa?
Cuando esté seguro de que se han descargado correctamente todos los archivos del repositorio, dirijase a la clase "Menu.java" y ejecute el programa con el boton _Run Java_ ubicado en la esquina superior derecha. Esto debería abrir una terminal de comandos donde se dispondrá de toda la información necesaria para que siga el uso del programa, contamos con un menú sensillo y amigable.

En caso de que no comprenda el uso del menú, explicamos a continuación su funcionamiento:
### Primer menú
Al iniciar el programa se le dará la bienvenida y se le presentarán 4 opciones, cada una enumerada desde 1, puede elegir cualquiera de estas opciones para seguir con el programa. 

La primera opción abrirá el menú para optimizar el tiempo de viaje, es decir, que abrirá un proceso para que pueda calcular cual es la ruta más rápida entre 2 nodos.

La segunda opción hará un proceso similar, pero este se orienta a la búsqueda del camino más barato entre 2 nodos.

La tercera opción termina la ejecución del programa.

La cuarta opcion abrirá un menú orientado a desarrolladores, muestra opciones para ver el estado actual del grafo empleado.
### Menú Tiempo
Este menú esta orientado a buscar el recorrido en teleférico más rápido entre 2 estaciones. Al ingresar se le pedirán 2 números, estos número corresponden a las estaciones que desea ingresar al algoritmo del programa, al lado de cada estación disponible se muestra el número que la identifica.

Una vez ingresadas las estaciones a buscar el programa dará inicio a los algoritmos necesarios para hallar la respuesta deseada. Esta respuesta se le mostrará dentro de la misma terminal, indicando el tiempo estimado y el recorrido que debe seguirse.

Al ingresar cualquier caractér el programa volverá a su menú principal.
### Menú Precio 
Este menú es bastante similar al menú anterior, con la diferencia de que al inicio se le pedirá una entrada más, indicando si usted es un cliente estándar o si cuenta con tarjeta de Estudiante o de Adulto Mayor. Esto se hace para calcular de manera correcta el costo de la ruta.
### Menú Dev
Este es un menú orientado a la visualización del estado actual del grafo y del set que contiene las líneas a las que pertenece cada nodo. Su función radica en hacer seguimiento del estado de estas estructuras de datos, es especialmente útil cuando se hacen modificaciones al input, cambios tales como adición de nuevas líneas, estaciones o conexiones.
## Explicación del algoritmo
En nuestro proyecto, comenzamos por explicar la base fundamental de los grafos, que son estructuras de datos utilizadas para representar conexiones entre elementos. Estos elementos se denominan nodos, mientras que las conexiones entre ellos se llaman aristas. Mostramos diferentes tipos de grafos: dirigidos, donde las aristas tienen una dirección específica; no dirigidos, donde las conexiones son bidireccionales; y ponderados, donde las aristas tienen un costo asociado, útil para representar precios o tiempos.

Luego, nos adentramos en los algoritmos que empleamos. El primero fue el algoritmo BFS (Breadth-First Search) o Búsqueda en Anchura, el cual busca el camino más corto entre dos nodos en un grafo no ponderado. Ilustramos su funcionamiento mediante una búsqueda por niveles, donde se procesan todos los nodos de un nivel antes de avanzar al siguiente. La complejidad algorítmica de BFS es O(V+E), siendo V el número de nodos y E el número de aristas. Este se utiliza en el proceso de cálculo de optimización de precio.

El segundo algoritmo que utilizamos fue el de Dijkstra, que encuentra el camino más corto entre dos nodos en un grafo ponderado. Explicamos cómo este algoritmo mantiene un registro de la distancia más corta desde el nodo de origen hasta cada nodo y cómo utiliza una cola de prioridad para seleccionar el próximo nodo a explorar. La complejidad algorítmica de Dijkstra es O((V+E)log V), donde V es el número de nodos y E es el número de aristas. Este algoritmo se usa para la optimización de tiempo.

Estos algoritmos proporcionan las bases sobre las cuales construimos nuestra aplicación SkyLink, permitiendo a los usuarios encontrar rutas óptimas en términos de tiempo y costo dentro del sistema de teleférico de La Paz.
## Conclusión
La conclusión del proyecto SkyLink destaca su papel fundamental en la mejora del transporte público en La Paz. Al emplear algoritmos avanzados como BFS y Dijkstra, esta aplicación ofrece a los usuarios una herramienta poderosa para encontrar rutas óptimas en el complejo sistema de teleférico de la ciudad. La meticulosa guía de instalación y puesta en marcha asegura que los usuarios puedan aprovechar plenamente la funcionalidad de SkyLink, mientras que la explicación detallada de los algoritmos subyacentes permite una comprensión más profunda de su funcionamiento.

La interfaz intuitiva de la aplicación facilita a los usuarios la selección de sus puntos de partida y destino, ya sea priorizando el tiempo o el costo. Esto se traduce en una experiencia de usuario mejorada, donde los viajeros pueden tomar decisiones informadas sobre su movilidad en la ciudad. Además, la inclusión de opciones específicas para diferentes tipos de usuarios, como estudiantes, refleja la atención a la diversidad de necesidades dentro de la población.

En última instancia, SkyLink aspira a no solo optimizar la eficiencia del transporte público, sino también a mejorar la calidad de vida de los ciudadanos de La Paz al facilitar sus desplazamientos diarios. Al ofrecer una solución integral y accesible, este proyecto representa un paso significativo hacia la creación de una ciudad más conectada, eficiente y habitable.

Con esto acaba la guía de uso de SkyNet, espero que disfrutes explorando por nuestro menú viendo las funciones que tiene y que te sea de gran ayuda.
