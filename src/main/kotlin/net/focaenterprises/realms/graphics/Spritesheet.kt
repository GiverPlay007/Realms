package net.focaenterprises.realms.graphics

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class Spritesheet(path: String) {
  private val spritesheet: BufferedImage = ImageIO.read(javaClass.getResource("/sprites/$path"))

  fun get(x: Int, y: Int, width: Int, height: Int): BufferedImage = spritesheet.getSubimage(x, y, width, height)
}