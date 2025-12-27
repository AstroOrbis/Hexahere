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
    id = "zerorange",
    name = "Range From Zero",
    description = "Returns the inclusive range between 0 and the provided integer.",
    category = HexPatternCategory.MATH,
    input = "num",
    output = "list"
)
object OpZeroRange : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        // this is literally just OpIntRange but with A = 0. yes im extremely lazy
        val A = 0
        val B = args.getInt(0, argc)
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
