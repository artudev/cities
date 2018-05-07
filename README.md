# CITIES 

**GitHub**

[Repository] https://github.com/artudev/cities

### Rules

We have a list of cities containing around 200k entries in JSON format. Each entry contains the following information:
{
 "country":"UA",
 "name":"Hurzuf",
 "_id":707860,
  "coord":{
   "lon":34.283333,
   "lat":44.549999
  }
}

* Rule 1: Display this of cities on a scrollable list in alphabetic order (city first, country after)
** "Denver, US" should appear before, "Sydney, Australia"
** "Anaheim, US" should appear before "Denver, US"
* Rule 2: Be able to filter the results by a given prefix string, over the city.
* Rule 3: Selecting a city will show a map centered on the coordinates associated with the city.
* Rule 4: Optimize for fast searches, loading time of the app is not so important
* Tech stack: Only 3rd party libraries allowed: GSON

### Tech

**Assets**

cities.json is stored in /assets directory

**External libraries used:**

  * [Gson] - A Java serialization/deserialization library to convert Java Objects into JSON and back
  * [Mockito] (tests only)  - Mocking framework for unit tests in Java

**Design patter:**

* MVP

**Code packages:**

* .parent - Base implementation for Activity and Fragment along with interfaces for them and Presenter. Handles binding for android views, animations, toasts, snackbars, navigation (changing fragments inside one activity). Just the stuff than need to be used everywhere in one place to avoid redundant code.
* .model - Collection of Helpers, Containers and Objects used in project. Includes models of City, Cooridinates and CitiesRepository. CitiesRepository is our main container for Cities collection and privides way of populeting itself both in background or in main thread. CitiesRepository is created and stroed in MainApplication as with such heavy and time consuming operations we want to be able to reuse this collection rather then rebuild it again for separate views. (Same goes to MappableHelperImpl which opertates on the same data and can also be used in other views).
* .model.assets - Utility to read file content and return it as a String;
* .model.json - Utility to parse json in String form into actuall java objects. 
* .model.sort - Contains implementation of our core sorting and searching engine. MappableHelperImpl. I decided to go with TreeMap as a holder for our Cities collection. While there are other options TreeMap gives us best result in all of them in my opinion. All: Random access by index/key, Search/Contains, Insert has O(log(n)) which is fast and ratio speed/size is even better the bigger collection gets. As we guess - cities collection won't get smaller any time soon. While being very fast this type of collection is also sorted which is rather important for us. 
* .list - Fragment, Contract and Presenter of our list screen as well as adapter and viewholder of our recuclerview. Contract as an interface describe a relation between Fragment and Presenter of this screen. Presenter is injected with CitiesRepository and MappableHelperImpl.
* .map - Fragment, Contract and Presenter of our map screen. Contract as an interface describe a relation between Fragment and Presenter of this screen. MapFragement here is treated as a child view of ListFragment which is handled by BaseActivity (setChildContent)

**Tests:**

Helpers have small tests writen for them. 
In case of AssetHelper testing is done with mockito as well. Context, AssetManager and InputStream are being mocked. InputStream is set to return fixed content upon any ::open() request.

**Summary:**

While application is working nicely I'm not very happy with a little messy code around Threads, Handlers and Callbacks. However there is no clean way to implement it. Having RxJava as a available tool I could make it clean and readable as well more responsive, compact and a lot more testable. Here otherwise because of shortage of time I had no time to create rxjava-similar engine to handle background operations. Other than that I am satisfied with my code.

License
----
MIT

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Mockito]: <http://site.mockito.org/>
   [Gson]: <https://github.com/google/gson>
   [Repository]: <https://github.com/artudev/cities/tree/master>
  
