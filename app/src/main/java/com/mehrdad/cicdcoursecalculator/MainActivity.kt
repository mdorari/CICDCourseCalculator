package com.mehrdad.cicdcoursecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mehrdad.cicdcoursecalculator.presentation.CalculatorScreen
import com.mehrdad.cicdcoursecalculator.ui.theme.CICDCourseCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CICDCourseCalculatorTheme {
                // A surface container using the 'background' color from the theme
                CalculatorScreen()
            }
        }
    }
}

