import java.util.Scanner

class Screen : ScreenHandler() {

    private val archieves: MutableList<Archieve> = mutableListOf()
    var currentArchieve: Int? = null
    var currentNote: Int? = null

    fun showScreen(screen: Screens) {

        when (screen) {
            Screens.ArchiveScreen -> {
                menuOutput("Доступные архивы: \n0. Создать архив", archieves)
                val input = menuSelection(archieves)
                if (input == archieves.size + 1) {
                    exit()
                } else if (input == 0) {
                    showScreen(Screens.CreateArchiveScreen)
                } else {
                    currentArchieve = input
                    showScreen(Screens.NotesScreen)
                }
            }

            Screens.NotesScreen -> {
                menuOutput(
                    "Заметки в архиве: \n0. Создать заметку",
                    archieves[currentArchieve!! - 1].notes
                )
                val input = menuSelection(archieves[currentArchieve!! - 1].notes)
                if (input == archieves[currentArchieve!! - 1].notes.size + 1) {
                    showScreen(Screens.ArchiveScreen)
                } else if (input == 0) {
                    showScreen(Screens.CreateNoteScreen)
                } else {
                    currentNote = input
                    showScreen(Screens.ShowNote)
                }
            }

            Screens.CreateArchiveScreen -> {
                println("Введите название архива:")
                val userInput = readLine()
                if (userInput != null) {
                    if (userInput.isEmpty()) {
                        println("вы ничего не ввели")
                    } else {
                        archieves.add(Archieve(userInput))
                        println("Добавлен архив $userInput")
                    }
                    showScreen(Screens.ArchiveScreen)
                }
            }

            Screens.CreateNoteScreen -> {
                println("Введите название заметки:")
                val noteName = readLine()
                println("Введите текст заметки:")
                val noteText = readLine()
                if (noteName != null && noteText != null) {
                    if (noteName.isEmpty()) {
                        println("Вы забыли добавить название заметки, создайте заметку заново")
                        showScreen(Screens.NotesScreen)
                    } else if (noteText.isEmpty()) {
                        println("Вы ничего не добавили в описание заметки, создайте заметку заново")
                        showScreen(Screens.NotesScreen)
                    } else {
                        println("Добавлена заметка: $noteName")
                        archieves[currentArchieve!! - 1].notes.add(Note(noteName, noteText))
                    }
                }
                showScreen(Screens.NotesScreen)
                /*noteName?.let { Note(it, noteText) }
                    ?.let { archieves[currentArchieve!!-1].notes.add(it) }*///вариант 1
            }

            Screens.ShowNote -> openNote()
        }
    }

    private fun openNote() {
        val noteName = archieves[currentArchieve!! - 1].notes[currentNote!! - 1].name
        val noteText = archieves[currentArchieve!! - 1].notes[currentNote!! - 1].text
        println("Название: $noteName")
        println("Текст: $noteText")

        do {
            println("Нажмите 0 для выхода.")
            val pressExit = Scanner(System.`in`).nextLine()
        } while (pressExit != "0")
        showScreen(Screens.NotesScreen)
    }
}