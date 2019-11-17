package com.learngradle

import com.learngradle.domain.Person

class PersonImpl {

    public static void main(String[] args) {

        Person person = new Person("dilip", "s")
        person.setFirstName("dilip1")
        println "person is $person"

        Person person1 = new Person("dilip", "s")
        println("Are those true? : ${person==person1}")
    }
}
