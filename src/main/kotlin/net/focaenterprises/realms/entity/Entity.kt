package net.focaenterprises.realms.entity

import net.focaenterprises.realms.game.RealmsInfo.TILE_SIZE
import java.awt.Graphics
import java.awt.image.BufferedImage

abstract class Entity(
  var x: Double,
  var y: Double,
  var width: Int = TILE_SIZE,
  var height: Int = TILE_SIZE,
  var sprite: BufferedImage? = null
) {

  var removed = false
    private set

  abstract fun update()

  fun render(graphics: Graphics) {
    sprite?.let {
      graphics.drawImage(it, x.toInt(), y.toInt(), width, height, null)
    }
  }

  fun remove() {
    removed = true
  }
}