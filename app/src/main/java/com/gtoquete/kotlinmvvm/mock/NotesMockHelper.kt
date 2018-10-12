package com.gtoquete.kotlinmvvm.mock

import com.gtoquete.kotlinmvvm.data.model.Note

object NotesMockHelper {

    val notesMockList: List<Note>
        get() {
            return listOf(Note("Lembrete", "Comprar comida amanhã"),
                    Note("Teste", "Isso é um lembrete!"),
                    Note("Sou um título", "teste de conteúdo"))
        }
}