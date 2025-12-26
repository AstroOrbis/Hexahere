package com.astroorbis.hexahere.utils

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.ListIota
import org.jetbrains.annotations.NotNull

fun iotaListCartesian(list1: @NotNull List<Iota>, list2: @NotNull List<Iota>): @NotNull List<ListIota> {
    val cartesianProduct: MutableList<ListIota> = mutableListOf();

    for (i in list1) {
        for (j in list2) {
            cartesianProduct.add(ListIota(listOf(i, j)))
        }
    }

    return cartesianProduct.toList()
}

fun iotaListCartesian(
    list1: @NotNull List<Iota>,
    list2: @NotNull List<Iota>,
    list3: @NotNull List<Iota>
): @NotNull List<ListIota> {
    val cartesianProduct: MutableList<ListIota> = mutableListOf();

    for (i in list1) {
        for (j in list2) {
            for (k in list3) {
                cartesianProduct.add(ListIota(listOf(i, j, k)))
            }
        }
    }

    return cartesianProduct.toList()
}
