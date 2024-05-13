# Guía de edición del Programa
SkyLink fue desarrollado con la idea de que el programa debe poder actualizarse fácilmente, esto para no necesitar de grandes modificaciones en caso de que se cambie la información que trata, es por esto que toda la información del grafo empleado para el seguimiento de las líneas del teleférico es ingresada desde el archivo [input](input.txt).

## Input
Input es empleado para crear las estructuras de datos e ingresar toda la información necesaria para el funcionamiento correcto del programa. Se divide de la siguiente manera:
### Estructura General del Grafo
Esta sección está conformada por 3 datos, el número de nodos, el número de aristas y el número de líneas.

El número de nodos se refiere a la cantidad de estaciones del Teleférico que existen, sin diferenciar si una estación pertenece a 2 o más líneas.

El número de aristas se refiere a la cantidad de conexiones que existen entre distintas estaciones.

Finalmente, el número de líneas hace referencia a cuantas líneas hay en todo el sistema del Teleférico.

### Distribución de las Líneas
En esta sección se ingresa una secuencia de pares de números *_n_* y *_m_*. Donde *_n_* representa a un nodo y *_m_* representa su pertenencia a una de las líneas del Teleférico. Se sigue el siguiente orden para los identificadores de las líneas:
* Azul -> 0
* Roja -> 1
* Naranja -> 2
* Blanca -> 3
* Cafe -> 4
* Celeste -> 5
* Verde -> 6
* Amarilla -> 7
* Plateada -> 8
* Morada -> 9
    
Un nodo puede pertenecer a varias líneas, estos nodos representan las estaciones que sirven como intersección entre líneas.
Esta sección del input se terminará con el ingreso de un _-1_

<div id="lineas">
  <ul align="center">
    <img height="400" width="500" alt="JPG" src="https://i.postimg.cc/rFy0BFCh/photo1715609299.jpg">
    </ul>
</div>

### Creación del Grafo Ponderado
Esta sección se ingresa una secuencia de _x_ líneas, donde _x_ deberá ser igual al número de aristas que se introdujo en la primera sección del programa.

Se ingresan 3 datos por línea, el primer y el segundo dato corresponden a los identificadores numéricos de un par de nodos. El tercer dato corresponde al peso que se le asigna al trayecto entre estos 2 nodos, en el caso de este programa, el peso representa tiempo de viaje medido en minutos.

## Consideraciones
Para facilitar la visualización del estado actual del grafo se agregaron varios métodos de prueba, entre los cuales se incluyen la visualización del grafo en formato de listas y un método que muestra la distribución de las líneas, ambos pueden ser utilizados directamente desde el menú ingresando a la opción "DevTest".

Es importante recalcar que gran parte de los datos obtenidos para la creación del programa fueron extraidos directamente de la [Página oficial de MiTeleférico](https://www.miteleferico.bo/).
