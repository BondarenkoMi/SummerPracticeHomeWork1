package ru.itis.homework1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editTextNumber)
        button.setOnClickListener()
        {
            val number = Integer.valueOf(editText.text.toString())
            race(listOfCars(number))
        }
    }
}

fun compareCars(car1: Car, car2: Car): Car {
    if (car1.power > car2.power)
        return car1
    else
        return car2
}

fun race(listOfCars: MutableList<Car>) {
    var cars = listOfCars
    while (cars.size != 1) {
        var winners = mutableListOf<Car>()
        if (cars.size % 2 == 0) {
            for (i in 0..<cars.size / 2) {
                val car1 = cars[i * 2]
                val car2 = cars[i * 2 + 1]
                winners.add(compareCars(car1, car2))
                println("\nRace between " + car1.mark + " " + car1.model + " and " + car2.mark + " " + car2.model + ". Winner is :")
                compareCars(car1, car2).displayInfo()
                Thread.sleep(2000)
            }
            cars = winners
            println("\n\n\nNext round. Cars left ${cars.size}")
            Thread.sleep(3000)
        } else {
            winners.add(cars[0])
            cars.removeAt(0)
            for (i in 0..<cars.size / 2) {
                val car1 = cars[i * 2]
                val car2 = cars[i * 2 + 1]
                winners.add(compareCars(car1, car2))
                println("\nRace between " + car1.mark + " " + car1.model + " and " + car2.mark + " " + car2.model + ". Winner :")
                compareCars(car1, car2).displayInfo()
                Thread.sleep(2000)
            }
            cars = winners
            println("\n\n\nNext round. Cars left ${cars.size}")
            Thread.sleep(3000)
        }
    }
    println("\n\n\nWinner of race:")
    cars[0].displayInfo()
}

fun listOfCars(number: Int) = listOfAllCars.subList(0, number - 1)


open class Car(
    val mark: String,
    val model: String,
    val year: Int,
    val power: Int
) {
    open fun displayInfo() {
        println("Mark - $mark, model - $model, year - $year, power - $power hp.")
    }
}

class Jeep(
    mark: String,
    model: String,
    year: Int,
    power: Int,
    val drive: DriveTypes,
) : Car(mark, model, year, power) {
    override fun displayInfo() {
        super.displayInfo()
        println("DriveType - $drive.")
    }
}

class Sedan(
    mark: String,
    model: String,
    year: Int,
    power: Int,
    val trunkSize: Int
) : Car(mark, model, year, power) {
    override fun displayInfo() {
        super.displayInfo()
        println("Trunk size - $trunkSize.")
    }
}

class Coupe(
    mark: String,
    model: String,
    year: Int,
    power: Int,
    val isConvertible: Boolean,
) : Car(mark, model, year, power) {
    override fun displayInfo() {
        super.displayInfo()
        if (isConvertible)
            println("Roof is convertible.")
        else
            println("Roof isn`t convertible.")
    }
}

class Truck(
    mark: String,
    model: String,
    year: Int,
    power: Int,
    val capacity: Int
) : Car(mark, model, year, power) {
    override fun displayInfo() {
        super.displayInfo()
        println("Load capacity $capacity tons.")
    }
}

enum class DriveTypes {
    RWD,
    FWD,
    AWD
}

val listOfAllCars = mutableListOf(
    Jeep("BMW", "X5", 2020, 420, DriveTypes.AWD),
    Jeep("BMW", "X7", 2021, 500, DriveTypes.AWD),
    Jeep("Audi", "Q7", 2018, 440, DriveTypes.AWD),
    Jeep("Mercedes", "GLE", 2019, 380, DriveTypes.FWD),
    Sedan("BMW", "M5", 2021, 540, 200),
    Sedan("BMW", "M3", 2022, 380, 170),
    Sedan("Mercedes", "C43", 2018, 350, 190),
    Sedan("Mercedes", "E63", 2020, 450, 220),
    Sedan("Audi", "A6", 2022, 320, 215),
    Coupe("BMW", "M4", 2023, 430, false),
    Coupe("BMW", "Z4", 2016, 370, true),
    Coupe("Audi", "A5", 2020, 305, false),
    Coupe("Porsche", "911", 2021, 465, true),
    Truck("Scania", "R580", 2015, 580, 70),
    Truck("Scania", "R480", 2012, 480, 50),
    Truck("Volvo", "FH16", 2018, 720, 85)
).shuffled().toMutableList()
