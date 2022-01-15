package net.focaenterprises.realms.world.grid

class GridOutOfBoundsException(override val message: String) : IndexOutOfBoundsException() {
  companion object {
    fun byCoords(x: Int, y: Int): GridOutOfBoundsException {
      return GridOutOfBoundsException("Invalid coordinates for x = $x, y = $y")
    }

    fun byCoords(x: Int, y: Int, width: Int, height: Int): GridOutOfBoundsException {
      return GridOutOfBoundsException("Invalid coordinates for x = $x, y = $y where width = $width, height = $height")
    }
  }
}