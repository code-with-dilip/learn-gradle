package com.learngradle.collections

class CollectionsExample {

    def static closureExample(Set nums){
        nums.each {
            println(it)
        }

        nums.each { n-> println(n) }
        nums.eachWithIndex{ def entry, int i ->  println("$entry = $i")  }


    }

    public static void main(String[] args) {
        def nums = [1,2,3,4,6,5] as LinkedList
        println(nums)

        def numSet = [1,2,3,4,6,5,5,6,7] as Set
        println(numSet)

        closureExample(numSet)
    }
}
