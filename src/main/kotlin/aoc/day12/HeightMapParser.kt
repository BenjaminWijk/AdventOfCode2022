package main.kotlin.aoc.day12

import util.Grid

class HeightMapParser {

        private val charToHeight = mutableMapOf<Char, Int>().apply {
            for(c in 'a' .. 'z'){
                put(c, c.code-97)
            }

            put('S', get('a')!!) //Starting point, min height
            put('E', get('z')!!) //End point, max height
        }

        fun parseHeightMap(input: List<String>): HeightMap{
            val height = input.size
            val width = input[0].length

            val grid = Grid.createGrid(height, width){ y,x -> charToHeight[input[y][x]]!! }

            val letterGrid = Grid.createGrid(height, width){y, x -> input[y][x]}
            Grid.printGrid(height,width){ y,x ->
                letterGrid[y][x].toString()
            }

            return HeightMap(height,width, grid)
        }

}