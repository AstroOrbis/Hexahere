package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.DoubleIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.ListIota
import com.astroorbis.hexahere.annotations.HexPattern
import com.astroorbis.hexahere.annotations.HexPatternCategory

@HexPattern(
    id = "vec2list",
    name = "Vector to List",
    description = "Converts a vector to a list of 3 numbers",
    category = HexPatternCategory.TYPES,
    input = "vec",
    output = "list"
)
object OpVecToList : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val vec = args.getVec3(0, argc)

        return listOf(
            ListIota(listOf(DoubleIota(vec.x), DoubleIota(vec.y), DoubleIota(vec.z)))
        )
    }
}
