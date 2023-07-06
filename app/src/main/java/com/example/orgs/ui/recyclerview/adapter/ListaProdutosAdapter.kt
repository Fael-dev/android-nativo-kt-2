package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.models.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(biding: ProdutoItemBinding): RecyclerView.ViewHolder(biding.root) {
        private val nome = biding.produtoItemNome
        private val descricao = biding.produtoItemDescricao
        private val valor = biding.produtoItemValor
        private val biding = biding

        fun vincula(produto: Produto) {
            this.nome.text = produto.nome
            this.descricao.text = produto.descricao
            this.valor.text = formatarParaMoeadBrasileira(produto.valor)
            this.biding.imageView.load("https://img.freepik.com/fotos-gratis/frutas-sortidas-e-mistas_74190-6961.jpg?w=996&t=st=1688680456~exp=1688681056~hmac=df15635bc43baa818fa1226def0467d4818f631d9949b810e7d0602fcdd77838")
        }

        private fun formatarParaMoeadBrasileira(valor: BigDecimal): String {
            val formatador: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val biding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(biding)
    }

    override fun getItemCount(): Int = this.produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}
