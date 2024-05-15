package com.mehrdad.cicdcoursecalculator.domain

//doing this will help further changes to symbols. Symbols can be changed easily here, not in entire code
enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%'),
}

//to create a string of operation: "+-x/%"
val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char):Operation{
    return Operation.values().find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid symbol")
}