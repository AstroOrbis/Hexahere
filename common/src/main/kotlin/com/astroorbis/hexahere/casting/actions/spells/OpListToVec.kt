package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.SpellList
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getDouble
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.getPositiveInt
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.DoubleIota
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import net.minecraft.world.phys.Vec3

object OpListToVec : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {

        // this is basically just Flock's Disintegration into a Vector Exaltation but whatever



        var l = args.getList(0, argc)
        if (l.size() != 3)
            // no clue if this is correct lmfao
            throw MishapInvalidIota.of(l as Iota, 0, "int.between", 3, 3)

        val ll: List<Iota> = l.toList()

        return Vec3(
            ll.getDouble(0),
            ll.getDouble(1),
            ll.getDouble(2)
        ).asActionResult

    }
}
