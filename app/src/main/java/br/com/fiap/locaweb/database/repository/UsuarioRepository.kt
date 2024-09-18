package br.com.fiap.locaweb.database.repository

import android.content.Context
import br.com.fiap.locaweb.database.dao.UsuarioDb
import br.com.fiap.locaweb.model.UsuarioModel

class UsuarioRepository(context: Context) {

    private val db = UsuarioDb.getDatabase(context).usuarioDao()


    fun salvar(usuario: UsuarioModel): Long{
        return db.salvar(usuario)
    }

    fun atualizar(usuario: UsuarioModel): Int{
        return db.atualizar(usuario)
    }

    fun excluir(usuario: UsuarioModel): Int{
        return db.excluir(usuario)
    }

    fun buscarUsuarioPeloId(id: Long): UsuarioModel{
        return db.buscarPorID(id)
    }

    fun buscarTodos(id: Long): UsuarioModel{
        return db.buscarTodos()
    }

    fun buscarPorEmailESenha(email: String, senha : String): UsuarioModel{
        return db.buscarPorEmailESenha(email, senha)
    }

    fun atualizarTema(id: Long, novoTema: String): Boolean {
        return db.atualizarTema(id, novoTema) > 0
    }
}