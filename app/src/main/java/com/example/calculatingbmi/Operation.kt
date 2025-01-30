package com.example.calculatingbmi


import java.text.DecimalFormat

class Operation {

    companion object {
        fun calculateBmi(height: Int, weight: Int): String {
            var result = "0"
            if (weight == 0 || height == 0) {
                return result
            }
            val bmi: Double = weight.toString().toDouble() / ((height.toString()
                .toDouble() / 100) * (height.toString().toDouble() / 100))
            val decimalFormat = DecimalFormat("#.0")
            result = decimalFormat.format(bmi)
            return result
        }


        fun resultReferences(bmi: String): String {
            var result = ""
            when {
                bmi.toDouble() == 0.0 -> result = "Недостаточно данных!"
                bmi.toDouble() in 1.0..16.0 -> result = "Выраженный дефицит массы тела"
                bmi.toDouble() in 16.0..18.5 -> result = "Недостаточная масса тела"
                bmi.toDouble() in 18.5..25.0 -> result = "Нормальная масса тела"
                bmi.toDouble() in 25.0..30.0 -> result = "Избыточная масса тела(предожирение)"
                bmi.toDouble() in 30.0..35.0 -> result = "Ожирение 1-ой степени"
                bmi.toDouble() in 35.0..40.0 -> result = "Ожирение 2-ой степени"
                bmi.toDouble() in 40.0..500.0 -> result = "Ожирение 3-ой степени"
                bmi.toDouble() > 500.0 -> result = "Вы не человек"
                else -> "Некорректные данные"

            }
            return result
        }
    }

}