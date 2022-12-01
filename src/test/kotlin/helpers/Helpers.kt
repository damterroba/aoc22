package helpers

import extension.allInts
import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class Helpers {
    val pointA = Point(0, 0)
    val pointB = Point(1, 1)
    val l = Line(pointA, pointB)

    @Test
    fun `any given point has only 4 directly adjacents`() {
        assertEquals(4, Point(0, 0).adjacents().size)
    }

    @Test
    fun `adj gives correct adjacents for given Point`() {
        val intended = setOf<Point>(
            Point(-1, 0),
            Point(1, 0),
            Point(0, -1),
            Point(0, 1)
        )
        assertEquals(
            intended, Point(0, 0).adjacents()
        )
    }

    @Test
    fun `allAdj returns 8 points`() {
        assertEquals(8, Point(0, 0).allAdj().size)
    }

    @Test
    fun `allAdj gives correct adjacents for given Point`() {
        val intended = setOf(
            Point(-1, 0),
            Point(1, 0),
            Point(0, -1),
            Point(0, 1),
            Point(-1, -1),
            Point(-1, 1),
            Point(1, -1),
            Point(1, 1)
        )
        assertEquals(
            intended, Point(0, 0).allAdj()
        )
    }

    @Test
    fun `Line calculates its length correctly`() {
        assertEquals(sqrt(2.0), l.distance())
    }

    @Test
    fun `belongsTo returns correctly for given point and line`(){
        val p1 = Point(2,14)
        val p2 = Point(10, 46)
        val t = Line(p1,p2)

        assertTrue(Point(3,18).belongsTo(t))
        assertTrue(Point(-1, 2).belongsTo(t))
        assertFalse(Point(0,0).belongsTo(t))
    }

    @Test
    fun `allInts returns correct List from String`(){
        assertEquals(listOf(1,2,3), "123".allInts())
        assertEquals(listOf(1,2,3), "1,2,3".allInts(","))
        assertEquals(listOf(1,2,3), "1a2a3".allInts())
    }
}