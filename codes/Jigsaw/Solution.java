package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        Queue<JigsawNode> exploreList = new LinkedList<JigsawNode>();
        Set<JigsawNode> visitedList = new HashSet<JigsawNode>(1000);
        int searchedNodesNum = 0;

        /*
         * initial the begin node
         * and the end node
         */
        this.setBeginJNode(bNode);
        this.setEndJNode(eNode);
        exploreList.add(this.getBeginJNode());
        while (!exploreList.isEmpty()) {
            /*
             * search until the explore list is empty
             */
            ++searchedNodesNum;
            this.currentJNode = exploreList.remove();
            if (this.currentJNode.equals(getEndJNode())) {
                this.getPath();
                break;
            }

            JigsawNode[] nextNodes = new JigsawNode[]{
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };

            /*
             * move the present node to the four position
             * if the position is valid and
             * if the node has not been visited,
             * add it into the explore list
             */
            for (int i = 0; i < 4; ++i) {
                if (nextNodes[i].move(i) && !visitedList.contains(nextNodes[i])) {
                    visitedList.add(nextNodes[i]);
                    exploreList.add(nextNodes[i]);
                }
            }
        }

        /*
         * output the result
         */
        System.out.println("Jigsaw Completed");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + searchedNodesNum);
        System.out.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());
        System.out.println();
        return isCompleted();
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }

        int wrongPlace = 0; // 所有放错位的数码个数
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] != 0 && jNode.getNodesState()[index] != index) {
                wrongPlace++;
            }
        }

        int allDistance = 0; // 所有放错位的数码与其正确位置的距离之和
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] != 0 && jNode.getNodesState()[index] != index) {
                int rightRow = (jNode.getNodesState()[index] - 1) / dimension;
                int rightCol = (jNode.getNodesState()[index] - 1) % dimension;
                int wrongRow = (index - 1) / dimension;
                int wrongCol = (index - 1) % dimension;
                allDistance += (int) Math.sqrt(Math.pow(rightRow - wrongRow, 2) + Math.pow(rightCol - wrongCol, 2));
            }
        }

        /*
         * adjust the ratio of the three factors
         */
        int allEstimate = 4 * allDistance + 1 * wrongPlace + 1 * s;
        jNode.setEstimatedValue(allEstimate);
    }
}
