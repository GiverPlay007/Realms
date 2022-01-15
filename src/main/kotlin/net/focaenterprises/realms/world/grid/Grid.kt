package net.focaenterprises.realms.world.grid

import net.focaenterprises.realms.world.grid.GridOutOfBoundsException.Companion.byCoords

class Grid(private val width: Int, private val height: Int) {
  private val elements: Array<GridObject?> = arrayOfNulls(width * height)

  fun isFree(x: Int, y: Int): Boolean = if (checkOutOfBoundsSilent(x, y)) false else elements[toIndex(x, y)] == null

  operator fun get(x: Int, y: Int): GridObject? {
    checkBounds(x, y)
    return cast(x, y)
  }

  fun insert(x: Int, y: Int, element: GridObject): GridObject? {
    checkBounds(x, y)

    val previous = cast(x, y)
    elements[toIndex(x, y)] = element

    return previous
  }

  private fun cast(x: Int, y: Int): GridObject? = elements[toIndex(x, y)]

  private fun toIndex(x: Int, y: Int): Int = x + y * width

  private fun checkOutOfBoundsSilent(x: Int, y: Int): Boolean = x < 0 || x >= width || y < 0 || y >= height

  private fun checkBounds(x: Int, y: Int) {
    if (checkOutOfBoundsSilent(x, y)) {
      throw byCoords(x, y, width, height)
    }
  }
}