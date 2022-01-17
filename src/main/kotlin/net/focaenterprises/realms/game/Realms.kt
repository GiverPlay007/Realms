package net.focaenterprises.realms.game

import javax.swing.JFrame
import net.focaenterprises.realms.entity.TreeEntity
import net.focaenterprises.realms.game.RealmsInfo.SCREEN_HEIGHT
import net.focaenterprises.realms.game.RealmsInfo.SCREEN_WIDTH
import net.focaenterprises.realms.game.RealmsInfo.TITLE
import net.focaenterprises.realms.graphics.Spritesheet
import net.focaenterprises.realms.scheduler.Time
import net.focaenterprises.realms.world.World
import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.image.BufferStrategy

class Realms : Runnable {
  private lateinit var canvas: Canvas
  private lateinit var frame: JFrame
  private lateinit var bufferStrategy: BufferStrategy

  private lateinit var thread: Thread
  private var isRunning = false

  lateinit var ambientSprites: Spritesheet
  lateinit var world: World

  private var ticks = 0
  private var tps = 0
  private var fps = 0

  fun load() {
    canvas = Canvas()
    canvas.preferredSize = Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)

    frame = JFrame(TITLE).apply {
      defaultCloseOperation = JFrame.EXIT_ON_CLOSE
      isResizable = false
      add(canvas)
      pack()
      setLocationRelativeTo(null)
    }

    canvas.createBufferStrategy(3)
    bufferStrategy = canvas.bufferStrategy

    ambientSprites = Spritesheet("Ambient.png")
    world = World()

    world.addEntity(TreeEntity(this, 10.0, 10.0))
  }

  fun start() {
    frame.isVisible = true
    isRunning = true
    thread = Thread(this)
    thread.start()
    frame.requestFocus()
  }

  private fun update() {
    world.update()
  }

  private fun draw() {
    val graphics = bufferStrategy.drawGraphics

    graphics.color = Color(0x0)
    graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT)

    world.render(graphics)

    graphics.dispose()
    bufferStrategy.show()
  }

  override fun run() {
    val nsPerTick = 1_000_000_000 / 30.0

    var timer = Time.millis()
    var lastTime = Time.nano()
    var now: Long
    var unprocessed = 0.0

    var tps = 0
    var fps = 0

    while(isRunning) {
      now = Time.nano()
      unprocessed += (now - lastTime) / nsPerTick
      lastTime = now

      while(unprocessed >= 1) {
        update()
        ++tps
        ++ticks
        --unprocessed
      }

      draw()
      ++fps

      if(Time.millis() - timer >= 1000) {
        println("TPS: $tps | FPS: $fps")
        timer += 1000
        this.tps = tps
        this.fps = fps
        tps = 0
        fps = 0
      }
    }
  }
}