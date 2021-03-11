package com.snail.kotlin_learn

import android.util.Log


/**
 * @Author Snail
 * @Since 2021/3/11
 */
class BaseLearn {

}

var addMethod: (Int, Int) -> Int = { number1, number2 -> number1 + number2 }

fun main() {
    val result = addMethod(1, 2)
}

