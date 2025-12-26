package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getDouble
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import com.astroorbis.hexahere.annotations.HexPattern
import com.astroorbis.hexahere.annotations.HexPatternCategory
import net.minecraft.world.phys.Vec3

@HexPattern(
    id = "list2vec",
    name = "List to Vector",
    description = "Converts a list of 3 numbers to a vector",
    category = HexPatternCategory.TYPES,
    input = "list",
    output = "vec"
)
object OpListToVec : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        // this is basically just Flock's Disintegration into a Vector Exaltation but whatever

        val l = args.getList(0, argc)
        if (l.size() != 3)
        // no clue if this is correct lmfao
            throw MishapInvalidIota.of(args[0], 0, "int.between", 3, 3)

        val ll: List<Iota> = l.toList()

        return Vec3(
            ll.getDouble(0),
            ll.getDouble(1),
            ll.getDouble(2)
        ).asActionResult

    }
}
