package net.focaenterprises.realms.world

import net.focaenterprises.realms.entity.Entity
import java.awt.Graphics
import java.util.concurrent.LinkedTransferQueue

class World {
  private val entities = HashSet<Entity>()
  private val entityAddQueue = LinkedTransferQueue<Entity>()

  fun update() {
    entities.forEach(Entity::update)
    entities.removeIf(Entity::removed)

    while(entityAddQueue.size > 0) {
      entities.add(entityAddQueue.poll())
    }
  }

  fun render(graphics: Graphics) {
    entities.forEach { it.render(graphics) }
  }

  fun addEntity(entity: Entity) {
    entityAddQueue.add(entity)
  }
}