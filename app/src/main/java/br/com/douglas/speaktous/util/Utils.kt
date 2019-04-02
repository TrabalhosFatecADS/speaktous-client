package br.com.douglas.speaktous.util

import android.widget.Toast

fun validateEmailFormat(email: String): Boolean {
    return if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        true
    } else false
}