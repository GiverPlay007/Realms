package net.focaenterprises.realms.entity

import net.focaenterprises.realms.game.Realms
import net.focaenterprises.realms.game.RealmsInfo.TILE_SIZE

class TreeEntity(realms: Realms, x: Double, y: Double) : Entity(realms, x, y) {
  override fun update() {

  }

  init {
    sprite = realms.ambientSprites.get(0, 0, TILE_SIZE, TILE_SIZE)
  }
}