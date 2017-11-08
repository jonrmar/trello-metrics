package br.com.metrics

interface Metric {
    fun calculate(args: Array<String>): Int
}