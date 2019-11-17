# Groovy

## String Interpolation

-   The **$** symbol is replaced with the actual content.

```aidl
   println "hi $firstName,from  doLast!"
```

## AST (Abstract Syntax Tree Transformation)

-   This concept in general is going to generate the toString method for us.

```aidl
@ToString(includeNames = true) @EqualsAndHashCode
// Abstract Syntax Tree Transformation
class Person {

    String firstName
    String lastName

    Person(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }

}

```

## Collections

-   Define a collection using the below code. By default it creates a collection of type **List**

```aidl
def nums = [1,2,3,4,6,5]
```

-   Using the **as** operator it can be changed to some other type.
    -   The below code takes care of changing the type from list to set

```aidl
    def numSet = [1,2,3,4,6,5,5,6,7] as Set
```
