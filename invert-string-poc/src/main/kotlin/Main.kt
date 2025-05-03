package org.java

import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter your string to be converted: ")
    val world = scanner.nextLine()
    val reversed: String? = reverseCleanString(world)
    println("Inverted string (no special characters): $reversed");
    scanner.close();
}

// This function takes a string as input, removes special characters, and reverses it.
fun reverseCleanString(word: String): String {
    // Remove special characters
    val cleanString = word.replace(Regex("[^A-Za-z0-9]"), "")
    // Reverse the string
    val reversedString = cleanString.reversed()
    return reversedString
}