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

package io.github.moulberry.notenoughupdates.util;



import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NpcUtils {

	public static boolean isNpc(Entity entity) {
		if (!(entity instanceof EntityOtherPlayerMP)) {
			return false;
		}
		return !TabListUtils.getTabListPlayersSkyblock().contains(entity.getName());
	}

	public static Entity getEntityCuttingOtherEntity(Entity e, Class<?> entityType) {
		List<Entity> possible = Minecraft.getMinecraft().theWorld.getEntitiesInAABBexcluding(e, e.getEntityBoundingBox().expand(0.3D, 2.0D, 0.3D), a -> {
			boolean flag1 = (!a.isDead && !a.equals(Minecraft.getMinecraft().thePlayer));
			boolean flag2 = !(a instanceof EntityArmorStand);
			boolean flag3 = !(a instanceof net.minecraft.entity.projectile.EntityFireball);
			boolean flag4 = !(a instanceof net.minecraft.entity.projectile.EntityFishHook);
			boolean flag5 = (entityType == null || entityType.isInstance(a));
			return flag1 && flag2 && flag3 && flag4 && flag5;
		});
		if (!possible.isEmpty())
			return Collections.min(possible, Comparator.comparing(e2 -> e2.getDistanceToEntity(e)));
		return null;
	}

	public static String stripString(String s) {
		char[] nonValidatedString = StringUtils.stripControlCodes(s).toCharArray();
		StringBuilder validated = new StringBuilder();
		for (char a : nonValidatedString) {
			if (a < '' && a > '\024')
				validated.append(a);
		}
		return validated.toString();
	}

	public static int getEntityHp(EntityArmorStand aStand) {
		double mobHp = -1.0D;
		Pattern pattern = Pattern.compile(".+?\\s([.\\d]+)[BMk]?/[.\\d]+[BMk]?.*");
		String stripped = StringUtils.stripControlCodes(aStand.getName());
		Matcher mat = pattern.matcher(stripped);
		if (mat.matches()) {
			try {
				mobHp = Double.parseDouble(mat.group(1));
				return (int)Math.ceil(mobHp);
			} catch (NumberFormatException ignored) {

			}
		}

		Pattern pattern2 = Pattern.compile(".+?\\s(\\d+)+[BMk]?.*");
		Matcher mat2 = pattern2.matcher(stripped);
		if (mat2.matches()) {
			try {
				mobHp = Double.parseDouble(mat2.group(1));
				return (int)Math.ceil(mobHp);
			} catch (NumberFormatException ignored) {

			}
		}
		return -1;
	}

	@SuppressWarnings("unused")
	private static final String[] npcNames = {
		"Golden Goblin",
		"Goblin",
		"Weakling",
		"Fireslinger",
		"Executive Viper",
		"Grunt",
		"Eliza",
		"Fraiser",
		"Wilson",
		"Ceanna",
		"Carlton",
		"Treasure Hoarder",
		"Team Treasuire",
		"Star Centry"
	};

}
