package net.focaenterprises.realms.scheduler

object Time {
  fun nano(): Long = System.nanoTime()

  fun millis(): Long = System.currentTimeMillis()
}