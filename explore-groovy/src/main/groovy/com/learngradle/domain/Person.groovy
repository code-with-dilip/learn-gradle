package com.learngradle.domain

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames = true) @EqualsAndHashCode
@Canonical
// Abstract Syntax Tree Transformation
class Person {

    String firstName
    String lastName

    Person(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }


}

