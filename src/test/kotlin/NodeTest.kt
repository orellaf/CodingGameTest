import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeTest {

    class Node(val value: Int, val right: Node? = null, val left: Node? = null)

    private fun buildRootNode(): Node = Node(
        value = 9,
        left = Node(
            value = 7,
            left = Node(
                value = 5,
                left = Node(
                    value = 2
                ),
                right = Node(
                    value = 6
                )
            ),
            right = Node(
                value = 8
            )
        ),
        right = Node(
            value = 13,
            right = Node(
                value = 17,
                left = Node(
                    value = 16
                )
            )
        )
    )

    @Test
    fun `find 8, OK`() {
        val rootNode = buildRootNode()

        val find = find(rootNode, 8)
        Assertions.assertNotNull(find)
        Assertions.assertEquals(8, find!!.value)
    }

    @Test
    fun `find 16, Ok `() {
        val rootNode = buildRootNode()

        val find = find(rootNode, 16)
        Assertions.assertNotNull(find)
        Assertions.assertEquals(16, find!!.value)
    }


    @Test
    fun `find 0, KO `() {
        val rootNode = buildRootNode()

        val find = find(rootNode, 0)
        Assertions.assertNull(find)
    }


    private fun find(n: Node, v: Int) : Node? {
        if (n.value == v) return n

        if(n.left != null && v < n.value) return this.find(n.left!!, v)

        if(n.right != null && v > n.value) return this.find(n.right!!, v)

        return null
    }

}





