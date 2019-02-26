# ConnectedCitiesAppV2FactoryPattern

Refactoring ConnectedCitiesApp - Spring Boot REST API to find connected cities
Whats new: Factory Design Pattern, DisjointUnionSets


Changes in Technical Specifications from previous version:

Technology Stack:

Maven, Spring boot, Java8, Junit5, Swagger2, Factory Design Pattern

Graph Search Algorithm:
The following graph search algorithms are implemented:
1.	Depth First Search (DFS) 
2.	Breadth First Search (BFS)
3.	DisjointSets

The choice of the algorithm depends on the value of the connectedcities.graphsearch.algorithm property in the application.properties file. 


Updated Design Considerations:

1.	Advantages of Spring boot:

    The @SpringBootApplication annotation is equivalent to using     @Configuration, @EnableAutoConfiguration and @ComponentScan with their default attributes

If there are beans/components in other packages that are not sub-packages of the main package, you should manually add them as @ComponentScan
 
In a non-Spring Boot Project, we would typically define the component scan explicitly in an XML application context or a Java Application Context

SpringBoot can autodetect the “application.properties” file in the “src/main/resources” directory and any loaded properties can be injected easily. There is no need to tell the Spring where the property file is located or even explicitly register a ‘PropertySource’. 

2.	Advantages of using @GetMapping over @RequestMapping at the method level:
               
     At the handler methods level,  It is best to use the more specific @GetMapping than the lengthier @RequestMapping(method=RequestMethod.GET) because with @RequestMapping, there is a possibility that the method attribute will be left off, as it is not mandatory.
But it is always best practice to be specific about the HTTP method that will be handled and @GetMapping is specific for the GET requests.


3.	Advantages of using Adjacency List over Adjacency Matrix:
                       
 The graph is represented as an Adjacency List. Adjacency list uses less memory when to compared to Adjacency matrix, there is a lot of wastage of memory especially if the matrix is sparse.
It is faster to iterate over all the edges in the Adjacency matrix as the adjacent vertices can be accessed directly.
And it is faster to add/delete a vertex.
Generally, Adjacency matrix representation is well suited when the graph is expected to be dense and Adjacency lists representation is well suited when the graph is expected to be sparse.

4.	In the City class, the instance variable Id is an Integer type and not an int. Because it is possible to check for null by using an Integer type.

5.	The city pairs from the input file is stored in a set to avoid duplicate entries.

6.	Enum Comparison. Enum members are compared using == operator rather than equals() methos. This is because == operator never throws a NullPointerException and is subject to type compatibility check at compile time and is faster than equals() method.

7.	Factory design pattern is used here to promote loose coupling, high cohesion and extensibility by extracting the responsibility of creating a new instance and handling it only in the factory class, it avoids repeatability of certain code. Since we are dealing with conditional instantiation of the graph search algorithms, Factory design pattern is a good choice.

8.	The package structure of the project is designed to reduce coupling across packages, avoid circular dependencies and support high cohesion.

9.	Switch statement is used in the place of if-else statement in the RouteFinderFactory.getFinderAlgorithm() method for the following reasons:

•	Switch is better for multiway branching: In the case of switch statement, the compiler is aware that the case statements are all same type and simply must be compared for equality with the switch expression, while in case of if expressions, the compiler has no such knowledge. This is because when compiler compiles a switch statement, it will inspect each of the case constants and create a “jump table” that it will use for selecting the path of execution depending on the value of the expression. Therefore, if we need to select among a large group of values, a switch statement will run much faster than the equivalent logic coded using a sequence of if-elses.

•	Speed:  If a switch contains more than five items, it’s implemented using a lookup table or a hash list. This means that all items get the same access time, compared to a list of if:s where the last item takes much more time to reach as it has to evaluate every previous condition first.

Here we are having less than 5 case values where there is not much difference in speed between the switch statement and the if-else statement but we have preferred the use of switch statement for the purpose of extensibility

•	Better Readability.



10.	We use if statement to check for null before entering the switch statement because switch(i) would throw a NullPointerException if I is null.
