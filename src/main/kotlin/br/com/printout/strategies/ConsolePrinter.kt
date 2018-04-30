package br.com.printout.strategies

import br.com.printout.PrinterStrategy

class ConsolePrinter : PrinterStrategy {

    override fun type(): Int {
        return 1
    }

    override fun print(text: String) {
        println(text)
    }
}