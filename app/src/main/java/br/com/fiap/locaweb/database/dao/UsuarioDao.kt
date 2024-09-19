package br.com.fiap.locaweb.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.locaweb.model.UsuarioModel

@Dao
interface UsuarioDao {

    @Insert
    fun salvar(usuarioModel: UsuarioModel): Long

    @Update
    fun atualizar(usuarioModel: UsuarioModel): Int

    @Delete
    fun excluir(usuarioModel: UsuarioModel): Int

    @Query("SELECT * FROM tbl_usuario WHERE id = :id")
    fun buscarPorID(id: Long): UsuarioModel

    @Query("SELECT * FROM tbl_usuario")
    fun buscarTodos(): UsuarioModel

    @Query("SELECT * FROM tbl_usuario WHERE email = :email and senha = :senha")
    fun buscarPorEmailESenha(email : String, senha : String): UsuarioModel

    @Query("UPDATE tbl_usuario SET tema = :novoTema WHERE id = :id")
    fun atualizarTema(id: Long, novoTema: String): Int

}