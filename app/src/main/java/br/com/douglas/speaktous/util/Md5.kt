package br.com.douglas.speaktous.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun getMd5(input: String): String {
    try {

        val md = MessageDigest.getInstance("MD5")

        val messageDigest = md.digest(input.toByteArray())

        val no = BigInteger(1, messageDigest)

        var hashtext = no.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        return hashtext
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}