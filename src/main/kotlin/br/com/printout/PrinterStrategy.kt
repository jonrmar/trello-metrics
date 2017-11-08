package br.com.printout

interface PrinterStrategy {
    fun type(): Int
    fun print(text: String)
}