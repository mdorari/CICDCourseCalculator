package com.mehrdad.cicdcoursecalculator.domain

//to determine each part of this expression eg: 36+6-(5*2)
sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Op(val operation: Operation) : ExpressionPart
    data class Parentheses(val type: ParenthesesType) : ExpressionPart
}

sealed interface ParenthesesType {
    object Opening : ParenthesesType
    object Closing : ParenthesesType
}