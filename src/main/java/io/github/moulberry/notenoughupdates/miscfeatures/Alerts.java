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

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.moulberry.notenoughupdates.NotEnoughUpdates;
import io.github.moulberry.notenoughupdates.autosubscribe.NEUAutoSubscribe;
import io.github.moulberry.notenoughupdates.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


@NEUAutoSubscribe
public class Alerts {
	public static Boolean paused = false;
	public Boolean found = false;
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if (Minecraft.getMinecraft().thePlayer != null && event.phase == TickEvent.Phase.END &&
			NotEnoughUpdates.INSTANCE.config.hidden.dev) {
			if (NotEnoughUpdates.INSTANCE.config.macroSafety.joinAlerts) {
				if (!paused) {
					String player = checkForPlayers();
					if (player!=null) {
						paused = true;
						Utils.addChatMessage(ChatFormatting.RED+"[ALERT] "+ChatFormatting.WHITE+player+ChatFormatting.RED+" joined the lobby!");
						switch (NotEnoughUpdates.INSTANCE.config.macroSafety.alertAction){
							case 1:
								FishingHelper.pause();
								break;
							case 2:
								Minecraft.getMinecraft().thePlayer.sendChatMessage("/leave");
								break;
						}


					}
				} else if (checkForPlayers()==null ) {
					Utils.addChatMessage("Resuming!");
					paused = false;
					FishingHelper.resume();
				}
			} else paused = false;
		}
	}

	private String checkForPlayers() {
		String playerlist = Utils.getPlayerList();
		for(String player : playerlist.split(" ") ) {
			if (NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist.contains(player) && player.length()>=4){
				return player;
			}
		}
		return null;
	}

}
