package com.ntuesoeoop.progressproject

import kotlin.math.max

class Progress {
    private var name: String

    private var level: Int

    private var streak: Int
    private var maxStreak: Int

    private var isCompleted: Boolean
    private var totalCompleted: Int
    private var targetCompleted: Int

    private var useTargetNum: Boolean
    private var targetNum: Float
    private var currentNum: Float

    private var count: Float
    private var targetCount: Float

    private var isEnded: Boolean


    constructor(name: String, useTargetNum: Boolean, targetNum: Float = 0f) {
        this.name = name

        this.level = 0

        this.streak = 0
        this.maxStreak = 0

        this.isCompleted = false
        this.totalCompleted = 0
        this.targetCompleted = 0

        this.useTargetNum = useTargetNum
        this.targetNum = targetNum
        this.currentNum = 0f
        this.count = 0f
        this.targetCount = 0f

        this.isEnded = false

    }

    public fun getName(): String {
        return this.name
    }

    public fun setName(name: String) {
        this.name = name
    }

    public fun getStreak(): Int {
        return this.streak
    }

    public fun setStreak(streak: Int) {
        if (streak >= 0) {
            this.streak = streak
        }
    }

    public fun increaseStreak() {
        this.streak += 1
    }

    public fun resetStreak() {
        this.streak = 0
    }

    public fun getMaxStreak(): Int {
        return this.maxStreak
    }

    public fun updateMaxStreak() {
        this.maxStreak = max(this.maxStreak, this.streak)
    }

    public fun getLevel(): Int {
        return this.level
    }

    public fun setLevel(level: Int) {
        if (level >= 0) {
            this.level = level
        }
    }

    public fun increaseLevel() {
        this.level += 1
    }

    public fun getCount(): Float {
        return this.count
    }

    public fun setCount(count: Float) {
        if (count >= 0) {
            this.count = count
        }
    }

    public fun getIsCompleted(): Boolean {
        return this.isCompleted
    }

    public fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
    }

    public fun getTotalCompleted(): Int {
        return this.totalCompleted
    }

    public fun getTargetCompleted(): Int {
        return this.targetCompleted
    }

    public fun getCompletedRatio(): String{
        return "{${this.totalCompleted} / ${this.targetCompleted}}"
    }

    public fun getTargetCount(): Float {
        return this.targetCount
    }

    public fun setTargetCount(targetCount: Float) {
        if (targetCount >= 0) {
            this.targetCount = targetCount
        }
    }

    public fun increaseTargetCount(increment: Int) {
        this.targetCount += increment
    }

    public fun getUseTargetNum(): Boolean {
        return this.useTargetNum
    }

    public fun getTargetNum(): Float {
        return this.targetNum
    }

    public fun setTargetNum(targetNum: Float) {
        this.targetNum = max(targetNum, 0f)
    }

    public fun getCurrentNum(): Float {
        return this.currentNum
    }

    public fun setCurrentNum(currentNum: Float) {
        this.currentNum = max(currentNum, 0f)
    }

    public fun getIsEnded(): Boolean {
        return this.isEnded
    }

    public fun setIsEnded(isEnded: Boolean) {
        this.isEnded = isEnded
    }

    public fun evaluate() {
        if (this.useTargetNum) {
            if (this.currentNum >= this.targetNum) {
                this.isCompleted = true
            }
        }

        if (this.isCompleted) {
            this.streak += 1
            this.totalCompleted += 1
            this.updateMaxStreak()
        }

        this.targetCompleted += 1
        this.count += this.currentNum
        this.targetCount += this.targetNum
    }
}