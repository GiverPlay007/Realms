package net.focaenterprises.realms.world.grid

class GridSquareNotFreeException(override val message: String?): RuntimeException() {
  companion object {
    fun bySquare(x: Int, y: Int): GridSquareNotFreeException {
      return GridSquareNotFreeException("Element cannot be placed at ($x, $y)")
    }
  }
}