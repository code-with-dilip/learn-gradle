package com.learngradle.collections

class CollectionsExample {

    def static closureExample(Set nums){
        nums.each {
            println(it)
        }

        nums.each { n-> println(n) }
        nums.eachWithIndex{ def entry, int i ->  println("$entry = $i")  }

    }

     def static map(){

         def map = [a:1, b:2, c:3]
         map.d = 4
         map['e'] = 5
         map.put('f', 6)
         println(map)
         map.each {e-> println("map[$e.key] and value is $e.value")}
         map.each {k,v -> println("Key is $k and value is $v")}

     }

    static void main(String[] args) {
        def nums = [1,2,3,4,6,5] as LinkedList
        println(nums)

        def numSet = [1,2,3,4,6,5,5,6,7] as Set
        println(numSet)
        closureExample(numSet)
        map()
    }
}
