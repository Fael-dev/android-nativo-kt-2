package com.example.orgs.extensions

import android.widget.ImageView
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(com.example.orgs.R.drawable.error) // Quando a URL/Image é null
        error(com.example.orgs.R.drawable.error) // Quando a string URL não é válida
        placeholder(com.example.orgs.R.drawable.placeholder)
    }
}