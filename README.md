# Punk Beers App

Aplicación desarrollada en Kotlin usando en los principios de Clean Architecture, para mostrar el
listado cervezas, se utiliza la API de punkapi.com y también es posible consultar el detalle de cada
cerveza, y consultar cervezas por nombre.

## Detalles de la arquitectura

Para el desarrollo de esta aplicación se ha dividido en módulos, *app* y *catalog*, este último
módulo a su vez está dividido en distintos submódulos: *data*, *domain* y *presentation*, de esta
manera se puede utilizar el módulo en otras aplicaciones, o por ejemplo si se hiciera una pantalla
de *configuración* con idioma u otras características, es más fácil reusarlo en otros proyectos ya
que es suficiente con mover o copiar el módulo a otra aplicación y mantener la estructura de
directorios.

También se hace uso de *usecases* para conectar la capa de presentation con la de domain.

Además se incluyen distintos tests unitarios que comprueban el correcto funcionamiento de los
*mappers*, llamadas de retrofit, *viewmodels* y test *end to end* con espresso para validar la ui
simulando la navegación del usuario en un emulador.

## Librerías y tecnologías utilizadas

- MVVM & Viewmodel
- Coroutinas
- Dagger Hilt
- Retrofit 2
- Flow
- Junit
- Mockk
- Jetpack Compose
- Material Design
- Coil
- Moshi
- Android Navigation Component
