package main.kotlin.aoc.day11

import main.kotlin.util.HighestValueStore
import util.FileHandler
import util.printIt

class D11Tasks(input: List<String>) {

    val monkeyParser = MonkeyParser(input)

    fun calcTopMonkeyBusinessAfterRounds(rounds:Int, worryLevelDecreases: Boolean): Long{
        val monkeys = monkeyParser.synthesizeMonkeys()

        for(round in 1 .. rounds){
            monkeys.forEach {
                while(it.canInspectItem){
                    it.inspectAndToss(monkeys, worryLevelDecreases)
                }
            }
        }

        val highestMonkeyBusiness = HighestValueStore(2)
        monkeys.forEach { highestMonkeyBusiness.put(it.monkeyBusinessCounter) }

        var monkeyBusinessScore = 1L
        highestMonkeyBusiness.values.forEach { monkeyBusinessScore *= it }

        return monkeyBusinessScore
    }

}

fun main(){
    val d11Tasks = D11Tasks(FileHandler.getLinesFromFile("day11.txt"))
    //Task A, answer: 66124
    //d11Tasks.calcTopMonkeyBusinessAfterRounds(20, true).printIt()
    //Task B, answer: TBD. Technically correct solution, it just has to run for "some" time...... :D
    //d11Tasks.calcTopMonkeyBusinessAfterRounds(10000, false).printIt()

    val d11SampleTasks = D11Tasks(FileHandler.getLinesFromFile("day11Sample.txt"))
    //Sample, answer: 10605
    d11SampleTasks.calcTopMonkeyBusinessAfterRounds(20, true).printIt()
    //Sample, answer:
 //  d11SampleTasks.calcTopMonkeyBusinessAfterRounds(10000, false).printIt()

}