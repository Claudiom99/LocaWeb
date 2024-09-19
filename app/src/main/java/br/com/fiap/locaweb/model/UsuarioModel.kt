package br.com.fiap.locaweb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuario")
data class UsuarioModel(

        @PrimaryKey(autoGenerate = true)
        var id : Long = 0,
        var nome : String = "",
        var email: String = "",
        var senha: String = "",
        var tema: Boolean = true
        )
