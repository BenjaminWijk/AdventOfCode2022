package main.kotlin.aoc.day8

import main.kotlin.util.printResult
import util.FileHandler


fun main(){
    val forest = Forest(FileHandler.getLinesFromFile("day8.txt"))

    forest.noOfVisibleTrees().printResult()

    val sampleForest = Forest(FileHandler.getLinesFromFile("day8Sample.txt"))
    sampleForest.noOfVisibleTrees().printResult()

}