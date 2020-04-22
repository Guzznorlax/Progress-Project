package com.ntuesoeoop.progressproject

import kotlin.math.max

class Progress {
    private var id: Int  // ID
    private var name: String  // 名稱
    private var description: String  // 說明

    private var level: Int  // 等級
    private var exp: Float // 累積分數  TODO 決定計分方式

    private var streak: Int  // 連續達成週期數
    private var maxStreak: Int // 最高連續達成週期數

    private var isCompleted: Boolean // 今日是否達成
    private var totalCompleted: Int // 總共達成數

    private var period: Int  // 週期
    private var passedPeriod: Int  // 經過週期
    private var currentCompleted: Int  // 目前達成日數（本週期間）
    private var targetCompleted: Int  // 目標達成日數 （週期內應完成天數）

    private var useTargetNum: Boolean  // 是否紀錄數字
    private var targetNum: Float  // 目標數量
    private var currentNum: Float  // 目前數量

    private var count: Float  // 總共數量
    private var targetCount: Float  // 總共應達成數量 -> targetNum * passedPeriod * passedPeriod

    private var isEnded: Boolean // 是否結束紀錄


    constructor(
        name: String,
        period: Int = 1,
        useTargetNum: Boolean = false,
        targetNum: Float = 0f,
        description: String = "",
        id: Int = 0
    ) {
        this.id = id
        this.name = name

        this.description = description

        this.level = 0
        this.exp = 0f

        this.streak = 0
        this.maxStreak = 0

        this.period = period
        this.passedPeriod = 0
        this.currentCompleted = 0

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

    public fun getId(): Int {
        return this.id
    }

    public fun getName(): String {
        return this.name
    }

    public fun setName(name: String) {
        this.name = name
    }

    public fun setDescription(description: String) {
        this.description = description
    }

    public fun getDescription(): String {
        return this.description
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

    public fun getCompletedRatio(): String {
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