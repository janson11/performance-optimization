# performance-optimization

## Java性能调优实战

### 1、如何制定性能调优标准？

- 有哪些参考因素可以体现系统的性能？

  **CPU**：有的应用需要大量计算，他们会长时间、不间断地占用CPU资源，导致其他资源无法争夺到CPU而响应缓慢，从而带来系统性能问题。例如：代码递归导致的无限循环，正则表达式引起的回溯，JVM频繁的FULL GC，以及多线程编程造成的大量上下文切换等，这些都有可能导致CPU资源繁忙。

  **内存**：Java程序一般通过JVM对内存进行分配管理，主要是用于JVM中的堆内存来存储Java创建的对象。系统堆内存的读写速度非常快，所以基本不存在读写性能瓶颈。但是由于内存成本要比磁盘高，相比磁盘，内存的存储空间又非常有限。所以当内存空间被占满。对象无法回收时，就会导致内存溢出、内存泄漏等问题。
  
  **磁盘I/O**：磁盘相比内存来说，存储空间要大很多，但磁盘I/O读写的速度要比内存慢，虽然目前引入的SSD固态硬盘已经有所优化，但仍然无法与内存的读写速度相提并论。
  
  **网络**：网络对于系统性能来说，也起着至关重要的作用。如果你购买过云服务，一定经历过，选择网络带宽大小这一环节。带宽过低的话，对于传输数据比较大，或者是并发量比较大的系统，网络就很容易成为性能瓶颈。
  
  **异常：**Java应用中，抛出异常需要构建异常栈，对异常进行捕获和处理，这个过程非常消耗系统性能。如果在高并发的情况下引发异常，持续地进行抛出异常处理，那么系统的性能就会明显地受到影响。
  
  **数据库**：大部分系统都会用到数据库，而数据库的操作往往是涉及到磁盘I/O的读写。大量的数据库读写操作，会导致磁盘I/O性能瓶颈，进而导致数据库操作的延迟性。对于有大量数据库读写操作的系统来说，数据库的性能优化是整个系统的核心。
  
  **锁竞争**：在并发编程中，我们经常会需要多个线程，共享读写操作同一个资源，这个时候为了保证数据的原子性(即保证这个共享资源在一个线程写的时候，不被另一个线程修改)，我们就会用到锁。锁的使用可能会带来上下文的切换，从而给系统带来性能开销。JDK1.6之后，Java为了降低锁竞争带来的上下文切换，对JVM内部锁已经做了多次优化，例如：新增了偏向锁、自旋锁、轻量级锁、锁粗化、锁消除等。而如何合理使用锁资源，优化锁资源，就需要你了解更多的操作系统的知识、Java多线程编程基础、积累项目经验，并结合实际场景去处理相关问题。
  
- 衡量一般系统的性能指标？

  **响应时间**：响应时间越短，性能越好，一般一个接口的响应时间是在毫秒级。在系统中 ，我们可以把响应时间自下而上细分为以下几种：

  1. 数据库响应时间：数据库操作所消耗的时间，往往是整个请求链中最耗时的。

  2. 服务端响应时间：服务端包括Nginx分发的请求所消耗的时间以及服务端程序执行所消耗的时间。

  3. 网络响应时间：这是网络传输时，网络硬件需要对传输的请求进行解析等操作所消耗的时间。

  4. 客户端响应时间：对于普通的Web、APP客户端来说，消耗时间是可以忽略不计的，但如果你的客户端嵌入了大量的逻辑处理，消耗的时间就有可能变长，从而成为系统的瓶颈。

​        **吞吐量** :在测试中，我们往往会比较注重系统接口的TPS(每秒事务处理量)，因为TPS体现了接口的性能，TPS越大，性能越好。在系统中，我们也可以把吞吐量自下而上地分为两种:磁盘吞吐量和网络吞吐量。

​       **计算机资源分配使用率**：通常由CPU占用率、内存使用率、磁盘I/O、网络I/O来表示资源使用率。

​		**负载承受能力**：当系统压力上升时，你可以观察，系统响应时间的上升曲线是否平缓。这项指标能直观地反馈给你，系统所能承受的负载压力极限。



2、如何制定性能调优策略？

![](C:\Users\janso\Pictures\性能调优\性能调优策略.png)

3、字符串性能

String类被final修饰，代表该类不可继承，而char[] value被final+private修饰，代表了String对象不可被更改。Java实现的这个特性叫作String对象的不可变性，即String对象一旦创建成功，就不能再对它进行改变。

Java这样做的好处在哪里？

- 保证String对象的安全性，假设String对象是可变的，那么String对象将可能被恶意修改。

- 保证hash属性值不会频繁变更，确保了唯一性，使得类似HashMap容器才能实现相应的key-value缓存功能。

- 可以实现字符串常量池。在Java中，通常有两种创建字符串对象的方式，一种是通过字符串常量的方式创建，如

  String str="abc";另一种是字符串变量通过new形式的创建，如String str=new String("abc")。

  当代码中使用第一种方式创建字符串对象时JVM首先会检查改对象是否存在字符串常量池中，如果在就返回该对象引用，否则新的字符串在常量池中被创建，这种方式可以减少同一个值的字符串对象的重复创建，节约内存。

4、正则表达式

![image-20200827192532870](C:\Users\janso\AppData\Roaming\Typora\typora-user-images\image-20200827192532870.png)

- 如何避免回溯问题

  1. 贪婪模式(Greedy)

     在数量匹配中，如果单独使用+、？、*或{min，max}等量词，正则表达式会匹配尽可能多的内容

     例如：test="abbc" regex="ab{1,3}c"

     在贪婪模式中，NFA自动机读取了最大的匹配范围，即匹配3个b字符。匹配发生了一次失败，就引起了一次回溯。如果匹配结果是"abbbc",就会匹配成功。test="abbbc" regex="ab{1,3}c"

  2. 懒惰模式（Reluctant）

     在改模式下，正则表达式会尽可能少地重复匹配字符，如果匹配成功，它会继续匹配剩余的字符串。

     例如：在上面例子的字符串后面添加一个"?",就可以开启懒惰模式。

     例如：test="abc" regex="ab{1,3}?c"

     匹配结果是"abc"，改模式下NFA自动机首先选择最小的匹配范围，即匹配1个b字符，因此就避免了回溯问题。

  3. 独占模式（Possessive）

     同贪婪模式一样，独占模式一样会最大限度地匹配更多的内容，不同的是，在独占模式下，匹配失败就会结束匹配，不会发生回溯问题。

     例如：test="abbc" regex="ab{1,3}+c"

     结果就是不匹配，结束匹配，不会发生回溯问题，

 **避免回溯的方法就是：使用懒惰模式和独占模式**





### 常用的数据结构

- 数组：采用一段连续的存储单元来存储数据。对于指定下标的查找，时间复杂度为O(1),但在数组中间以及头部插入数据时，需要复制移动后面的元素。

- 链表：一种在物理存储单元上非连续、非顺序的存储结构，数据元素的逻辑顺序是通过链表中的指针次序实现的。

  链表由一系列结点（链表中每一元素）组成，结点可以在运行时动态生成。每个结点都包含“存储数据单元的数据域”和“存储下一个结点地址的指针域”这两个部分。

  由于链表不用必须按顺序存储，所以链表在插入的时候可以达到O(1)的复杂度，但查找一个结点或者访问特定编号的结点需要O(n)的时间

- 哈希表：根据关键码值（Key value）直接进行访问的数据结构。通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫做哈希函数，存放记录的数组就叫哈希表。比如：hashMap就是基于哈希表实现的。

  哈希冲突：采用链地址法解决哈希冲突问题。这种方法是采用了数组（哈希表）+链表的数据结构，当发生哈希冲突时，就用一个链表结构存储相同的Hash值的数据。

  但是这种方式又存在一个性能问题，如果链表过长，查询数据的时间复杂度就会增加，HashMap在Java8中使用了红黑树来解决链表过长导致的查询性能下降的问题。

- 树：由n（n>=1）个有限结点组成的一个具有层次关系的集合，就像一颗倒挂的树。



### 如何优化I/O操作

- 使用缓冲区优化读写流操作
- 使用DirectBuffer减少内存复制
- 避免阻塞。NIO发布之后，通道和多路复用器这两个基本组件实现了NIO的非阻塞。



### 零拷贝

在I/O复用模型中，执行读写I/O 操作依然是阻塞的，在执行读写I/O操作时，存在着多次内存拷贝和上下文切换，给系统增加了性能开销。

零拷贝是一种避免多次内存复制的技术，用来优化读写I/O 操作。

在网络编程中，通常由read、write来完成一次I/O读写操作。每一次I/O读写操作都需要完成四次内存拷贝，路径是I/O设备—>内核空间—>用户空间—>内核空间—>其他I/O设备。

Linux内核中的mmap函数可以代替read、write的I/O读写操作，实现用户空间和内核空间共享一个缓存数据。mmap将用户空间的一块地址和内核空间的一块地址同时映射到相同的一块物理内存地址，不管是用户空间还是内核空间都是虚拟地址，最终要通过地址映射到物理内存地址。这种方式避免了内核空间与用户空间的数据交换。I/O复用中的epoll函数中就是使用了mmap减少了内存拷贝。

在Java的NIO编程中，则是使用了DirectBuffer来实现内存的零拷贝。Java直接在JVM内存空间之为开辟了一个物理内存空间，这样内核和用户进程都能共享一份缓存数据。



