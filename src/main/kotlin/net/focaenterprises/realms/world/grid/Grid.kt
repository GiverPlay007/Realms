package net.focaenterprises.realms.world.grid

class Grid(private val width: Int, private val height: Int) {
  private val elements: Array<GridObject?> = arrayOfNulls(width * height)

  fun isFree(x: Int, y: Int): Boolean = if (checkOutOfBoundsSilent(x, y)) false else elements[toIndex(x, y)] == null

  operator fun get(x: Int, y: Int): GridObject? {
    checkBounds(x, y)
    return cast(x, y)
  }

  fun insert(x: Int, y: Int, element: GridObject): GridObject? {
    checkBounds(x, y)
    checkCanPlace(x, y, element);

    val previous = cast(x, y)
    elements[toIndex(x, y)] = element

    return previous
  }

  fun canBePlaced(x: Int, y: Int, element: GridObject): Boolean {
    for(xx in x..x + element.width) {
      for(yy in y..y + element.height) {
        when {
          checkOutOfBoundsSilent(xx, yy) -> return false
          cast(xx, yy) != null -> return false
        }
      }
    }

    return true
  }

  private fun cast(x: Int, y: Int): GridObject? = elements[toIndex(x, y)]

  private fun toIndex(x: Int, y: Int): Int = x + y * width

  private fun checkOutOfBoundsSilent(x: Int, y: Int): Boolean = x < 0 || x >= width || y < 0 || y >= height

  private fun checkCanPlace(x: Int, y: Int, element: GridObject) {
    if(!canBePlaced(x, y, element)) {
      throw GridSquareNotFreeException.bySquare(x, y)
    }
  }

  private fun checkBounds(x: Int, y: Int) {
    if (checkOutOfBoundsSilent(x, y)) {
      throw GridOutOfBoundsException.byCoords(x, y, width, height)
    }
  }
}