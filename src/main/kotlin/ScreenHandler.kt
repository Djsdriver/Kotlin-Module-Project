import java.util.*

open class ScreenHandler {

    val scanner = Scanner(System.`in`)

    fun <T> menuOutput(title: String, list: List<T>) {
        println(title)
        list.forEachIndexed { index, it ->
            println("${index + 1}. ${it.toString()}")
        }
        println("${list.size + 1}. Выход")
    }


    fun <T> menuSelection(menu: List<T>): Int {
        var temp: Int?
        while (true) {
            while (!scanner.hasNextInt()) {
                println("Введите цифру, а не букву.")
                scanner.next()
            }
            temp = scanner.nextInt()
            if (temp !in 0..menu.size + 1) {
                println("Такого элемента не существует, попробуйте снова.")
                continue
            } else {
                return temp
            }
        }
    }

    fun exit() {
        return
    }
}