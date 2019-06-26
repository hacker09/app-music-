package com.example.musicfinal.utils

fun trimStringToLength(input: String, bound: Int): String {
    return if (input.length < bound) input else input.substring(0, bound)
}

fun isWordInString(strSearch: String, str: String): Boolean {
    val words = str.toLowerCase().split(" ".toRegex())
    for (word in words) {
        return strSearch.toLowerCase().contains(word.toLowerCase())
    }
    return false
}
