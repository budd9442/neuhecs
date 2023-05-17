/*
 * Copyright (C) 2023 NotEnoughUpdates contributors
 *
 * This file is part of NotEnoughUpdates.
 *
 * NotEnoughUpdates is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * NotEnoughUpdates is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotEnoughUpdates. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.moulberry.notenoughupdates.miscfeatures;


import io.github.moulberry.notenoughupdates.NotEnoughUpdates;
import io.github.moulberry.notenoughupdates.autosubscribe.NEUAutoSubscribe;
import io.github.moulberry.notenoughupdates.util.NpcUtils;
import io.github.moulberry.notenoughupdates.util.QolUtils;
import io.github.moulberry.notenoughupdates.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.List;

@NEUAutoSubscribe
public class PlayerESP {

	private final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onRenderWorldLastPlayerESP(RenderWorldLastEvent event) {
		if (mc.theWorld == null || mc.thePlayer == null) return;
		if(!NotEnoughUpdates.INSTANCE.config.esp.playerESP) return;
				List<EntityPlayer> players = mc.theWorld.playerEntities;
				for (EntityPlayer player : players) {
					if (player == mc.thePlayer) continue;
					if (NpcUtils.isNpc(player)) continue;
					if (player.getDistanceToEntity(mc.thePlayer) > 50) continue;

					QolUtils.drawEntity(player,Color.red, NotEnoughUpdates.INSTANCE.config.esp.thiccness, event.partialTicks);
					QolUtils.drawText(player.getName(), player.posX, player.posY + player.height + 0.3, player.posZ, false, 0.7f);

				}


	}
}
