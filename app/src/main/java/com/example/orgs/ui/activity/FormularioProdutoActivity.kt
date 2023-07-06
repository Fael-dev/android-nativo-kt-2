package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.models.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.biding.root)
        configuraBotaoSalvar()
        this.biding.activityFormProdutoImg.setOnClickListener {
            val bidingFormularioImagem = FormularioImagemBinding.inflate(layoutInflater)
            bidingFormularioImagem.formularioImagemBotaoCarregar.setOnClickListener {
                val url = bidingFormularioImagem.formularioImagemUrl.text.toString()
                bidingFormularioImagem.formularioImagemImgview.load(url)
            }
            AlertDialog.Builder(this)
                .setView(bidingFormularioImagem.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url = bidingFormularioImagem.formularioImagemUrl.text.toString()
                    this.biding.activityFormProdutoImg.load(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
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