package main.kotlin.com.soapsnake.kotlin.test

interface Sound {
    fun makeSound()
}

class CatSound : Sound {
    override fun makeSound() {
        println("CatSound::makeSound has been called")
        println("Meow!")
    }
}

class Animal(sound: Sound) : Sound by sound {
    fun performSound() {
        println("Animal::performSound has been called")
        makeSound() // 这里实际上调用的是 sound.makeSound()
    }
}

fun main() {
    val catSound = CatSound()
    val animal = Animal(catSound)
    animal.performSound() // 输出：Meow!
}