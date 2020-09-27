## 面试题
### 1、通过三种不同的方式创建了三个对象，再依次两两匹配，每组被匹配的两个对象是否相等？

```
String str1 = "abc";
String str2 = new String("abc");
String str3 = str2.intern();
log.info("str1是否等于str2:{}", (str1 == str2));
log.info("str2是否等于str3:{}", (str1 == str3));
log.info("str1是否等于str3:{}", (str1 == str3));
```

```
20:49:42.927 [main] INFO com.janson.performance.optimization.string.StringDemo - str1是否等于str2:false
20:49:42.933 [main] INFO com.janson.performance.optimization.string.StringDemo - str2是否等于str3:false
20:49:42.933 [main] INFO com.janson.performance.optimization.string.StringDemo - str1是否等于str3:true
原因分析：
1、在字符串常量中，默认会将对象放入常量池；
2、在字符串变量中，对象是会创建在堆内存中，同时也会在常量池中创建一个字符串对象，复制到堆内存对象中，并返回堆内存对象引用。
3、调用intern方法，会去查字符串常量池中是否有等于该对象的字符串，如果没有，就在常量池中新增改对象，并返回对象的引用；
如果有，就返回常量池中的字符串引用。堆内存中原有的对象由于没有引用指向它，将会通过垃圾回收器回收。
```



### 2 、我们在查看ArrayList的实现类源码时，你会发现对象数组elementData使用了transient修饰，我们知道transient关键字修饰该属性，则表示该属性不会被序列化，然而我们并没有看到文档中说明ArrayList不能被序列化，这是为什么？

由于ArrayList的数组是基于动态扩展的，所以并不是所有被分配的内存空间都存储了数据。如果采用外部化序列，会序列化整个数组。ArrayList为了避免这些没有存储数据的内存空间被序列化，内部提供了两个私有方法writeObject以及readObject来自我完成序列化与反序列化，从而在序列化与反序列化数组时节省了空间和时间。因此使用transient修饰数组，是防止对象数组被其他外部方法序列化。

### 3、我们在使用ArrayList进行新增、删除时，经常被提醒"使用ArrayList做新增删除操作会影响效率"。那是不是ArrayList在大量新增元素的场景下效率就一定会变慢呢？

由于ArrayList是数组实现的，而数组是一块连续的内存空间，在添加元素到数据头部的时候，需要对头部以后的数据进行复制重排，所以效率很低；而LinkedList是基于链表实现，在添加元素的时候，首先会通过循环查找到添加元素的位置，如果要添加的位置处于List的前半段，就从前往后找，若其位置处于后半段，就从后往前找。因此LinkedList添加元素到头部是非常高效的。

同上可知，ArrayList在添加元素到数组中间时，同样有部分数据需要复制重排，效率也不是很高；LinkedList虽然也不用循环查找元素，但LinkedList中多了new对象以及变换指针向对象的过程，所以效率要低于ArrayList。

### 4、如果让你使用for循环以及迭代循环遍历一个ArrayList，你会使用哪种方式呢？原因是什么？

for循环；因为ArrayList是基于数组实现的，并且实现了RandomAccess接口标志，意味着ArrayList可以实现快速随机访问，所以for循环效率高。

备注：在遍历LinkedList，切记不要使用for循环，要使用迭代。

