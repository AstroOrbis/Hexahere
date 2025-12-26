package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.ListIota
import com.astroorbis.hexahere.utils.iotaListCartesian

object OpCartesian3 : ConstMediaAction {
    override val argc = 3

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val A = args.getList(0, argc).toList()
        val B = args.getList(1, argc).toList()
        val C = args.getList(2, argc).toList()

        return listOf(ListIota(iotaListCartesian(A, B, C)))
    }
}
