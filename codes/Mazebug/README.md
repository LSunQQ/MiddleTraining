# MazeBug

走迷宫的思路大致就是：通过深度优先，不断探索新的路径，直至走到终点为止。其中会遇到分岔路，会有方向选择的问题，所以我在走的过程中记录他们走过的方向，通过概率选择下一个分岔路走的方向。

整个项目的主体就是`act()`函数：

- 在没有走到终点的时候，判断当前位置是否还能继续往下走

  - 如果能往下走的话，就在能往下走的位置中选择最佳的那一个方向继续往下走

    - 我使用一个一维数组来记录之前走过的方向，比如往北走一格之后，`count[0]++`。然后选择下一个要走的格子的时候，就根据这个数组，通过求和来将`0~sumOfCount`分为四个方向的概率，再利用随机数来选择。前面往某个方向走的越多，选取到这个方向的概率也就越大。

      ```java
      for (int i = 0; i < 4; ++i)
          sumOfProbability += directionProbability[i];
      int randomDirection = (int) (Math.random() * sumOfProbability);
      for (int i = 0; i < 4; ++i) {
          tempProbability += directionProbability[i];
          if (randomDirection < tempProbability && hasDirection[i]) {
              bestLocation = tempLocations.get(i);
              break;
          }
      }
      ```

      

  - 如果不能往下走的话，就返回上一个走过的节点

- 走到终点的时候，停止算法。