package com.ntuesoeoop.progressproject


import android.os.Build
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.max


@Entity(tableName = Progress.TABLE_NAME)
class Progress {

    companion object {
        const val TABLE_NAME = "progress"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0  // ID

    @NonNull
    private var name: String  // 名稱

    private var description: String?  // 說明

    private var level: Int  // 等級

    private var exp: Int // 累積分數  TODO 決定計分方式

    private var streak: Int  // 連續達成週期數

    private var maxStreak: Int // 最高連續達成週期數

    private var isCompleted: Boolean // 今日是否達成

    private var totalCompleted: Int // 總共達成數

    private var period: Int  // 週期
    private var passedPeriod: Int  // 經過週期
    private var passedDayInPeriod: Int   //經過天數(周期內)
    private var currentCompleted: Int  // 目前達成日數（本週期間）
    private var targetCompleted: Int  // 目標達成日數 （週期內應完成天數）

    private var useTargetNum: Boolean  // 是否紀錄數字
    private var targetNum: Int // 目標數量
    private var currentNum: Int  // 目前數量

    private var count: Int  // 總共完成數量
    private var targetCount: Int  // 總共應達成數量 -> targetNum * passedPeriod * targetCompleted

    private var updatedAt: String?
    private var createdAt: String?

    @Ignore
    private var isEnded: Boolean // 是否結束紀錄


    constructor(
        name: String,
        period: Int = 1,
        useTargetNum: Boolean = false,
        targetNum: Int = 0,
        description: String? = null,
        targetCompleted: Int = 1,
        createdAt: String? = null,
        updatedAt: String? = null,
        isCompleted: Boolean = false,
        currentCompleted: Int = 0,
        exp: Int = 0,
        streak: Int = 0,
        maxStreak: Int = 0,
        passedPeriod: Int = 0,
        passedDayInPeriod: Int = 0,
        totalCompleted: Int = 0,
        count: Int = 0,
        currentNum: Int = 0
    ) {
        this.name = name
        this.id = 0

        this.description = description

        this.level = 0
        this.exp = exp

        this.streak = streak
        this.maxStreak = maxStreak

        this.period = period
        this.passedPeriod = passedPeriod
        this.passedDayInPeriod = passedDayInPeriod
        this.currentCompleted = currentCompleted

        this.isCompleted = isCompleted
        this.totalCompleted = totalCompleted
        this.targetCompleted = targetCompleted

        this.useTargetNum = useTargetNum
        this.targetNum = targetNum
        this.currentNum = currentNum
        this.count = count
        this.targetCount = targetNum * targetCompleted * passedPeriod

        this.createdAt = createdAt
        this.updatedAt = updatedAt

        this.isEnded = false

        // println("${this.name} created at ${this.createdAt}")

    }

    public fun getName(): String {
        return this.name
    }

    public fun getExp(): Int {
        return this.exp
    }

    public fun setExp(exp: Int) {
        this.exp = exp
    }

    public fun getPeriod(): Int {
        return this.period
    }

    public fun getPassedPeriod(): Int {
        return this.passedPeriod
    }

    public fun setPassedPeriod(passedPeriod: Int) {
        this.passedPeriod = passedPeriod
    }

    public fun getPassedDayInPeriod(): Int {
        return this.passedDayInPeriod
    }

    public fun setPassedDayInPeriod(passedDayInPeriod: Int) {
        this.passedDayInPeriod = passedDayInPeriod
    }

    public fun getCurrentCompleted(): Int {
        return this.currentCompleted
    }

    public fun setCurrentCompleted(currentCompleted: Int) {
        this.currentCompleted = currentCompleted
    }

    public fun setName(name: String) {
        this.name = name
    }

    public fun setDescription(description: String?) {
        this.description = description
    }

    public fun getDescription(): String? {
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

    public fun setMaxStreak(maxStreak: Int) {
        this.maxStreak = maxStreak
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

    public fun getCount(): Int {
        return this.count
    }

    public fun setCount(count: Int) {
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

    public fun setTotalCompleted(totalCompleted: Int) {
        this.totalCompleted = totalCompleted
    }

    public fun getTargetCompleted(): Int {
        return this.targetCompleted
    }

    public fun setTargetCompleted(targetCompleted: Int) {
        this.targetCompleted = targetCompleted
    }

    public fun getCompletedRatio(): String {
        return "{${this.currentCompleted} / ${this.targetCompleted}}"
    }

    public fun getTargetCount(): Int {
        return this.targetCount
    }

    public fun setTargetCount(targetCount: Int) {
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

    public fun getTargetNum(): Int {
        return this.targetNum
    }

    public fun setTargetNum(targetNum: Int) {
        this.targetNum = max(targetNum, 0)
    }

    public fun getCurrentNum(): Int {
        return this.currentNum
    }

    public fun setCurrentNum(currentNum: Int) {
        this.currentNum = max(currentNum, 0)
    }

    public fun getIsEnded(): Boolean {
        return this.isEnded
    }

    public fun setIsEnded(isEnded: Boolean) {
        this.isEnded = isEnded
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun setUpdatedTime() {
        this.updatedAt = this.getFormattedCurrentTime()
        println("${this.name} Update time: ${this.updatedAt}")
    }

    fun setCreatedTime() {
        this.createdAt = this.getFormattedCurrentTime()
        println("${this.name} Create time: ${this.createdAt}")
    }

    fun getFormattedCurrentTime(): String {
        var date = Date()
        return ISODate().dateTimeFormatterSimple.format(date)
    }

    public fun getUpdatedAt(): String? {
        return this.updatedAt
    }

    public fun getCreatedAt(): String? {
        return this.createdAt
    }

    fun getUpdatedAtDate(): Date? {
        return this.updatedAt?.let { ISODate().dateTimeFormatterSimple.parse(it) }
    }

    fun getCreatedAtDate(): Date? {
        return this.createdAt?.let { ISODate().dateTimeFormatterSimple.parse(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPassedDay(): Long {
        if (this.updatedAt == null) {
            if (this.createdAt == null) {
                this.createdAt = ISODate().getFormattedCurrentTime()
            }

            this.updatedAt = this.createdAt
        }

        val nowDate =
            LocalDateTime.parse(ISODate().getFormattedCurrentTime(), ISODate().dateTimeFormatter)

        val startDate =
            LocalDateTime.parse(this.updatedAt, ISODate().dateTimeFormatter)

        return ChronoUnit.DAYS.between(startDate, nowDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun evaluate(): Boolean {
        val passedDay = this.getPassedDay()

        if (passedDay > 0) {
            if (this.useTargetNum && this.targetNum > 0) {
                this.isCompleted = this.currentNum >= this.targetNum
            }
            if (passedDay > 1) {
                //passedDay > 1
                if (this.isCompleted) {
                    this.currentCompleted += 1
                    this.isCompleted = false
                }
                this.currentNum = 0
                this.passedDayInPeriod += passedDay.toInt()
            } else {
                //passedDay = 1
                if (this.isCompleted) {
                    this.currentCompleted += 1
                    this.updateMaxStreak()
                    this.isCompleted = false
                }

                this.currentNum = 0
                this.passedDayInPeriod += 1
            }

            this.count += this.currentNum

            //calculate exp and level
            if (this.passedDayInPeriod >= this.period) {

                //exp get upgraded!
                if (this.currentCompleted >= this.targetCompleted) {
                    this.totalCompleted += 1
                    streak += 1

                } else {
                    this.streak = 0
                }

                val passedDayjudge: Int = this.passedDayInPeriod / period
                for (i in 1..passedDayjudge) {
                    this.passedPeriod += 1
                }
                this.passedDayInPeriod = 0
                this.currentCompleted = 0
                this.targetCount =
                    (this.targetNum * this.passedPeriod * this.targetCompleted).toInt()
            }

            this.exp = this.period * this.totalCompleted

            //level get upgraded!
            if (this.exp >= 1) {
                val levelupgrade: Int = this.exp.toInt() / 1
                for (i in 1..levelupgrade) {
                    this.level++
                }
            }

            println("Evaluating ${this.name} Passed Days: ${passedDay.toString()} ${this.totalCompleted} ${this.period}")
            this.setUpdatedTime()
            return true
        }
        //passedDay = 0
        return false
    }
}