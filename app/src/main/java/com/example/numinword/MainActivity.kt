package com.example.numinword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {

    private lateinit var editTv: AppCompatEditText
    private lateinit var btn: AppCompatButton
    private lateinit var tv: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTv = findViewById(R.id.editTv)
        btn = findViewById(R.id.btn)
        tv = findViewById(R.id.tv)

        btn.setOnClickListener {
            val number = editTv.text.toString().toInt()

            if (number < 1 || number > 1000) {
                tv.text = "Please enter a number between 1 and 1000."
            } else {
                val words = numberToWords(number)
                tv.text = words
            }
        }
    }
}

fun numberToWords(number: Int): String {
    val units = arrayOf(
        "", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი",
        "ექვსი", "შვიდი", "რვა", "ცხრა"
    )
    val teens = arrayOf(
        "",
        "თერთმეტი",
        "თორმეტი",
        "ცამეტი",
        "თოთხმეტი",
        "თხუთმეტი",
        "თექსვმეტი",
        "ჩვიდმეტი",
        "თვრამეტი",
        "ცხრამეტი"
    )
    val tens = arrayOf(
        "", "", "ოცდა", "ოცდა", "ორმოცდა", "ორმოცდა",
        "სამოცდა", "სამოცდა", "ოთხმოცდა", "ოთხმოცდა"
    )
    val hundreds = arrayOf(
        "", "ას", "ორას", "სამას", "ოთხას", "ხუთას",
        "ექვსას", "შვიდას", "რვაას", "ცხრაას"
    )

    if (number == 0) return units[0]
    if (number == 1000) return "ათასი"


    val lastDigit = number % 10
    val lastTwoDigits = number % 100
    val secondLastDigit = (number / 10) % 10

    if (secondLastDigit % 2 == 0) {
        val result = StringBuilder()
        val numberArray =
            number.toString().toCharArray().map { it.toString().toInt() }.toMutableList()
        while (numberArray.size < 3) {
            numberArray.add(0, 0)
        }

        if (numberArray[0] > 0) {
            if (secondLastDigit == 0 && lastDigit == 0) {
                result.append(hundreds[numberArray[0]] + "ი")
            } else {
                result.append(hundreds[numberArray[0]]).append(" ")
            }
        }

        if (numberArray[1] > 1) {
            if (lastTwoDigits == 10) {
                result.append("ათი")
            } else if (lastDigit == 0) {
                result.append(tens[numberArray[1]].dropLast(2) + "ი")
                result.append(units[numberArray[2]])
            } else {
                result.append(tens[numberArray[1]])
                result.append(units[numberArray[2]])
            }
        } else if (numberArray[1] == 1) {
            result.append(teens[numberArray[2] - 1])
        } else if (numberArray[1] == 0) {
            result.append(units[numberArray[2]])
        }
        return result.toString().trim()
    } else {

        val result = StringBuilder()
        val numberArray =
            number.toString().toCharArray().map { it.toString().toInt() }.toMutableList()
        while (numberArray.size < 3) {
            numberArray.add(0, 0)
        }

        if (numberArray[0] > 0) {
            result.append(hundreds[numberArray[0]]).append(" ")
        }

        if (numberArray[1] > 1) {
            if (lastDigit == 0) {
                result.append(tens[numberArray[1]] + "ათი")
                result.append(teens[numberArray[2]])
            } else {
                result.append(tens[numberArray[1]])
                result.append(teens[numberArray[2]])
            }
        } else if (numberArray[1] == 1) {
            if (lastTwoDigits == 10) {
                result.append("ათი")
            } else {
                result.append(teens[numberArray[2]])
            }
        } else if (numberArray[1] == 0) {
            result.append(teens[numberArray[2]])
        }
        return result.toString().trim()
    }
}




