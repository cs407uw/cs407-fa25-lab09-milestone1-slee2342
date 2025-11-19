package com.cs407.lab09

class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        android.util.Log.d("Ball", "pos: ($posX, $posY), vel: ($velocityX, $velocityY), acc: ($xAcc, $yAcc), dT: $dT")

        val newVelocityX = velocityX + 0.5f * (xAcc + accX) * dT
        val newVelocityY = velocityY + 0.5f * (yAcc + accY) * dT

        val deltaX = velocityX * dT + (1f / 6f) * dT * dT * (3f * accX + xAcc)
        val deltaY = velocityY * dT + (1f / 6f) * dT * dT * (3f * accY + yAcc)

        posX += deltaX
        posY += deltaY

        velocityX = newVelocityX
        velocityY = newVelocityY

        accX = xAcc
        accY = yAcc

        checkBoundaries()
    }

    fun checkBoundaries() {
        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }

        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }

        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
    }

    fun reset() {
        posX = backgroundWidth / 2f - ballSize / 2f
        posY = backgroundHeight / 2f - ballSize / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}