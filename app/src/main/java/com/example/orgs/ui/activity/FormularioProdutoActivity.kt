package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.models.Produto
import com.example.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.biding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()
        this.biding.activityFormProdutoImg.setOnClickListener {
            FormularioImagemDialog(this).mostra(url) { imagem ->
                url = imagem
                biding.activityFormProdutoImg.tentaCarregarImagem(url)
            }
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = this.biding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener {
            val produto = criaProduto()
            dao.adiciona(produto)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = this.biding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = this.biding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = this.biding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}