package com.muhasib.firebaselearning


class Person {
    var name: String? = null
    var age: String? = null // Change to String to match Firebase

    // No-argument constructor for Firebase
    constructor()

    // Parameterized constructor
    constructor(name: String?, age: String?) {
        this.name = name
        this.age = age
    }
}

