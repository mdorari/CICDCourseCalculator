package com.mehrdad.cicdcoursecalculator.domain

class ExpressionWriter {

    var expression = ""

    fun processAction(action: CalculatorAction){
        when(action){
            CalculatorAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())
                expression = evaluator.evaluate().toString()
            }
            CalculatorAction.Clear ->{
                expression = ""
            }
            CalculatorAction.Decimal -> {
                if (canEnterDecimal()){
                    expression += "."
                }
            }
            CalculatorAction.Delete -> {
                expression = expression.dropLast(1)
            }
            is CalculatorAction.Number -> {
                expression += action.number
            }
            is CalculatorAction.Op -> {
                if (canEnterOperation(action.operation)){
                    expression += action.operation.symbol
                }
            }
            CalculatorAction.Parentheses -> {
                processParentheses()
            }
        }
    }

    private fun prepareForCalculation(): String {
        //remove any operation, open parentheses and . from end of expression
        val newExpression = expression.takeLastWhile {
            it in "$operationSymbols(."
        }
        if(newExpression.isEmpty()){
            return "0"
        }
        return newExpression
    }

    private fun processParentheses() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }
        expression += when {
            //fist input -> (
            // it's an operation or opening parentheses -> (    eg:  1+(   15+2((
            expression.isEmpty() || expression.last() in "$operationSymbols(" -> "("
            //last input is number & there is no open parentheses to close -> no parentheses
            // eg: (1+5)*5 -> return
            expression.last() in "0123456789)" && openingCount == closingCount -> return
            //there is open parentheses to be closed -> )
            else -> ")"
        }
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        // for + -
        // expression is empty -> true
        // last part of expression is any of +-*/% -> true
        // last part of expression is number or () -> true
        if (operation in listOf(Operation.ADD,Operation.SUBTRACT)){
            return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }

        //for * / %
        // expression if empty -> false
        // last part of expression is number
        return expression.isNotEmpty() || expression.last() in "0123456789"
    }


    private fun canEnterDecimal(): Boolean {
        // expression is empty (it's first user's input
        // eg: .1 NotOK  but 0.1 isOK ) -> false
        // last char is +-*/%.() -> false
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()"){
            return false
        }
        // if last part contains . -> false
        return !expression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }
}