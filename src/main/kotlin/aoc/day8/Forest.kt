package main.kotlin.aoc.day8

import main.kotlin.util.Grid
import main.kotlin.util.Position

//Assuming input is always a perfect rectangle
class Forest(val input: List<String>) {

    val xSize: Int = input[0].length
    val ySize: Int = input.size

    val trees: MutableMap<Position, Tree> = mutableMapOf()

    init {
        populateAndConnect()
        calculateTreeVisibility()
    }
    fun noOfVisibleTrees(): Int = trees.values.count { it.visible }

    fun findHighestViewScore(): Int {
        var highest = 0

        trees.values.forEach{
            val score = it.calculateViewScore()
            if(score > highest) highest = score
        }

        return highest
    }

    private fun populateAndConnect() {
        //Tree with height creation
        Grid.processGrid(ySize, xSize) { y, x ->
            val height = input[y][x].digitToInt()
            trees[Position.of(y, x)] = Tree(height)
        }

        //"Everything is connected, man" - some hippie, probably
        Grid.processGrid(ySize, xSize) { y, x ->
            trees[Position.of(y, x)]?.apply {
                left = trees[Position.of(y, x - 1)]
                top = trees[Position.of(y - 1, x)]
                right = trees[Position.of(y, x + 1)]
                bottom = trees[Position.of(y + 1, x)]
            }
        }
    }

    private fun calculateTreeVisibility() {
        for (y in 0 until ySize) {
            //from left
            processTreeVisibilityFromAngle(trees[Position.of(y, 0)]!!) { right }
            //from right
            processTreeVisibilityFromAngle(trees[Position.of(y, xSize-1)]!!) { left }
        }

        for (x in 0 until xSize) {
            //from top
            processTreeVisibilityFromAngle(trees[Position.of(0, x)]!!) { bottom }
            //from bottom
            processTreeVisibilityFromAngle(trees[Position.of(ySize-1, x)]!!) { top }
        }
    }

   private fun processTreeVisibilityFromAngle(outerTree: Tree, next: Tree.() -> Tree?) {
        outerTree.visible = true

        var highest = outerTree.height
        var tree = outerTree.next()

        while (tree != null) {
            if (tree.height > highest){
                tree.visible = true
                highest = tree.height
            }
            tree = tree.next()
        }
    }

    data class Tree(
        val height: Int,
        var left: Tree? = null,
        var top: Tree? = null,
        var right: Tree? = null,
        var bottom: Tree? = null
    ) {
        var visible = false

        private fun getDirectionalScore(nextInDirection: Tree.() -> Tree?): Int{
            var tree = nextInDirection()

            var score = 0
            while(tree != null){
                score++

                if(tree.height >= height) break
                tree = nextInDirection(tree)
            }
            return score
        }

        fun calculateViewScore(): Int{
            val left = getDirectionalScore { left }
            val top = getDirectionalScore { top }
            val right = getDirectionalScore { right }
            val bottom = getDirectionalScore { bottom }

            return left * top * right * bottom
        }

    }
}

