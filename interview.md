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