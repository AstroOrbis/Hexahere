package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getInt
import at.petrak.hexcasting.api.casting.iota.DoubleIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.ListIota
import com.astroorbis.hexahere.annotations.HexPattern
import com.astroorbis.hexahere.annotations.HexPatternCategory

@HexPattern(
    id = "intrange",
    name = "Range",
    description = "Returns the inclusive range between two integers as a list of floats",
    category = HexPatternCategory.MATH,
    input = "num, num",
    output = "list"
)
object OpIntRange : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val A = args.getInt(0, argc)
        val B = args.getInt(1, argc)
        val l = if (A <= B) {
            (A..B).toList()
        } else {
            (A downTo B).toList()
        }.map { i ->
            DoubleIota(i.toDouble())
        }

        return listOf(ListIota(l))
    }
}
