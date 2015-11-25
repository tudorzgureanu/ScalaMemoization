# ScalaMemoization
Provides a simple (read as primitive) memoization functionality. For education purposes.

#### How to use it:

- Build and publish the artifact into your local repository.
- Use the resulted artifact in your SBT project:

`libraryDependencies += "com.ted.tools" %% "scala-memoization" % "0.1-SNAPSHOT"`

- You need also to add macro paradise compiler plugin for the scala version you are using (i.e. 2.11.7):

`addCompilerPlugin("org.scalamacros" % "paradise_2.11.7" % "2.1.0")`

- Then annotate the methods you want to memoize with `@memo` and that's all.

#### Example
```
    @memo
    def fibonacci(n: Int): Int = n match {
      case 0 | 1 => n
      case _ => fibonacci(n - 1) + fibonacci(n - 2)
    }
```

#####TODOs:
- Unit tests
- Change the current cache implementation (a simple mutable map) with a better one (with the possibility to specify the size and TTL).
