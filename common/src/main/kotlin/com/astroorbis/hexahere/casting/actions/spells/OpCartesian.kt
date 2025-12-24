package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.world.entity.Entity

object OpCartesian : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val A = args.getList(0, argc)
        val B = args.getList(1, argc);

        val out: MutableList<Iota> = mutableListOf();

        for (i: Iota in A) {
            for (j: Iota in B) {
                out.add(ListIota(listOf(i, j)));
            }
        }

        return listOf(ListIota(out))
    }
}
