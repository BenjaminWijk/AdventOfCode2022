package aoc.day8

import util.printIt
import util.FileHandler


fun main(){
    val forest = Forest(FileHandler.getLinesFromFile("day8.txt"))

    //Task A, answer: 1776
    forest.noOfVisibleTrees().printIt()
    //Task B, answer: 234416
    forest.findHighestViewScore().printIt()

    val sampleForest = Forest(FileHandler.getLinesFromFile("day8Sample.txt"))
    //21
    sampleForest.noOfVisibleTrees()
    //8
    sampleForest.findHighestViewScore()

}