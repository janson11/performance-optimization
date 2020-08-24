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