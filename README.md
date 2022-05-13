# Punk Beers App

Application developed in Kotlin using Clean Architecture principles.

The punkapi.com API is used to show the list of beers with paging, search by name and detail of each
beer.

## Architecture details

For the development of this application, it has been divided into modules, *app* and *catalog*, this
latest module is divided into different submodules: *data*, *domain* and *presentation*, in this way
you can move easily the module to other applications, or for example if you made a screen of *
configuration* with language and other features, it's easy to reuse it in other projects moving the
module to another application and keeping the structure of directories.

*usecases* are used to connect the presentation layer with the domain layer.

In addition, different unit tests are included to test mappers, retrofit calls, viewmodels and end
to end tests with espresso to validate the ui simulating user navigation in an emulator.

## Used libraries

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
-

## Screenshots

 <table>
  <tr>
     <td>List Screen with paging and search</td>
     <td>Details Screen</td>
  </tr>
  <tr>
    <td><img src="https://raw.githubusercontent.com/carlesaguilar/punkbeers/master/screenshots/list.png" height=500></td>
    <td><img src="https://raw.githubusercontent.com/carlesaguilar/punkbeers/master/screenshots/detail.png" height=500></td>
  </tr>
 </table>