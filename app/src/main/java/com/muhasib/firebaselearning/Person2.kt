

package com.muhasib.firebaselearning
data class Person2(
    var name: String? = null, // Nullable if you want to allow null values
    var age: String? = null,   // Change to String to match Firebase
    var documentId: String = "" // Keep this as a non-nullable String
)
