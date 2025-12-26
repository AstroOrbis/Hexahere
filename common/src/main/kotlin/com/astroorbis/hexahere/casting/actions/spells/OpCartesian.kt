package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.ListIota
import com.astroorbis.hexahere.annotations.HexPattern
import com.astroorbis.hexahere.annotations.HexPatternCategory
import com.astroorbis.hexahere.utils.iotaListCartesian

@HexPattern(
    id = "cartesian",
    name = "Cartesian",
    description = "Returns the cartesian product of 2 lists",
    category = HexPatternCategory.MATH,
    input = "list, list",
    output = "list"
)
object OpCartesian : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val A = args.getList(0, argc).toList()
        val B = args.getList(1, argc).toList()

        return listOf(ListIota(iotaListCartesian(A, B)))
    }
}
