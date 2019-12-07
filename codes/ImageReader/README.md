# ImageReader

`Image IO`：

- `myRead`：根据`bmp`文件的格式读取文件，注意信息的字节数的存储的对小端的。

  其中要存储的信息有图像宽度、图像高度、图像每个像素的位数以及每一个像素的`RGB`值。

  创建图像文件的时候使用了教程里的API `Toolkit.getDefaultToolkit().createImage()`

- `myWrite`：利用Java的API创建输出流即可



`Image Processor`：

三种颜色通道和灰度图的展示本质上其实都是一样的，就是将图片的每一个像素的值更改为需要的值就可以了

- 红色通道：`(red, 0, 0)`
- 绿色通道：`(0, green, 0)`
- 蓝色通道：`(0, 0, blue)`
- 灰度图： RGB三个值都为`red*0.299+green*0.587+blue*0.114`



`Image Processor Test`

这个`Junit Test`主要就是看看上面所实现的输入函数、图像处理函数有没有问题，所以我将测试分成了两个部分：

- 首先是图像输入的测试，我将图片分别使用我实现的`myRead()`函数进行读入和使用`Java`的API进行读入，比较两者的高度、宽度以及每一个像素的值

  ```java
  assertEquals(testImage.getHeight(null), standardImage.getHeight(null));
  assertEquals(testImage.getHeight(null), standardImage.getHeight(null));
  
  for (int i = 0; i < testBufferedImage.getHeight(); ++i) {
      for (int j = 0; j < testBufferedImage.getWidth(); ++j) {
          assertEquals(testBufferedImage.getRGB(j, i), standardImage.getRGB(j, i));
      }
  }
  ```

- 然后是将处理完之后(提取某个颜色通道)的图片与目标图片进行对比，同样是对比图像的高度、宽度以及每一个像素的值。