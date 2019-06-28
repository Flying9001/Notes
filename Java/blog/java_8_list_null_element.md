## 关于Java 8 中 list 集合 size 大于 0 包含 null 元素导致空指针异常(NullPointException)的问题与解决方案    


​    
​    
​    
​    
### 1 摘要  

java 中 list 是一种常用的集合类(接口)，起子类 `ArrayList` 更是重要的使用广泛； list 集合允许存在值为 `null` 的元素，在操作 list 元素时,值为 null 的对象可能会导致空指针异常(NullPointException)。下文将模拟 list 集合中 size 大于 0，同时包含 null 元素的情景，并针对该情况在 java 8 环境下给出一定的解决方案。

​    

### 2 情景模拟  

#### 2.1 模拟包含 null 元素的 list 集合  

```java
        int length = 10;

        /**
         * list 初始化并赋值
         */
        List<Integer> integerList = new ArrayList<>(16);
        for (int i = 0; i < length; i++) {
            if (i == 5 || i == 8) {
                integerList.add(null);
            } else {
                integerList.add(i);
            }
        }

        /**
         * list size
         */
        System.out.println("list size: " + integerList.size());

        /**
         * 打印 list 的每一个元素
         */
        integerList.stream().forEach(integer -> {
            System.out.println(integer);
            // TODO to do something

        });
```

运行结果:  

```
list size: 10
0
1
2
3
4
null
6
7
null
9
```

从结果可知,list 集合的长度为 10,其中包含 2 个值为 null 的元素  

​    

#### 2.2 模拟出现空指针异常的场景  

```java
        try {
            integerList.stream().forEach(integer -> {
                System.out.println(integer.toString());
                // TODO to do something

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
```

运行结果:  

```verilog
0
1
2
3
4
java.lang.NullPointerException
	at com.ljq.demo.object.ListDemoTest.lambda$listSize$1(ListDemoTest.java:50)
	at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1382)
	at java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:580)
	at com.ljq.demo.object.ListDemoTest.listSize(ListDemoTest.java:49)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
```

在 list 集合包含 null 元素时,如果再来操作 null 对象，则会抛出空指针异常(NullPointerException)  

​    

### 3 解决方案  

#### 3.1 当只操作原来的 list 时  

如果只需要在原来的 list 中进行操作，后期不再使用该 list 时，可以使用的解决方法:  

```java
        integerList.stream().filter(Objects::nonNull).forEach(integer -> {
            System.out.println(integer.toString());
            // TODO to do something

        });
```

java 8 中使用 lambda 表达式进行过滤 null 元素  

运行结果:  

```
0
1
2
3
4
6
7
9
```



#### 3.2 单次引用原来的 list 时  

如果原来的 list 需要被引用**单次**时,可以使用该解决方案:  

如将 list 转化为 json 字符串，同时过滤 null 元素  

```java
        try {
            String listJsonStr = new ObjectMapper().writeValueAsString(integerList.stream().filter(Objects::nonNull).peek(integer -> {
                System.out.println(integer.toString());
                // TODO to do something

            }).collect(Collectors.toList()));
            System.out.println("listJsonStr: " + listJsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
```

运行结果:  

```
0
1
2
3
4
6
7
9
listJsonStr: [0,1,2,3,4,6,7,9]
```

​    

#### 3.3 多次引用,生成新的 list  

当需要**多次**使用该 list，或使用 list 的不同属性时，可以过滤 null 元素之后生成一个新的 list  

在生成新的 list 之后，旧的 list 集合将会被弃用，可能会产生内存垃圾  

```java
        List<Integer> newIntegerList = integerList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println("newIntegerList size: " + newIntegerList.size());
        // TODO to do something
```

运行结果:  

```
newIntegerList size: 8
```

​    

### 4 完整测试代码  

```java
package com.ljq.demo.object;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListDemoTest {

    @Test
    public void listSize() {

        int length = 10;

        /**
         * list 初始化并赋值
         */
        List<Integer> integerList = new ArrayList<>(16);
        for (int i = 0; i < length; i++) {
            if (i == 5 || i == 8) {
                integerList.add(null);
            } else {
                integerList.add(i);
            }
        }

        /**
         * list size
         */
        System.out.println("list size: " + integerList.size());

        /**
         * 打印 list 的每一个元素
         */
        integerList.stream().forEach(integer -> {
            System.out.println(integer);
            // TODO to do something

        });

        System.out.println("---------------- 分割线 ----------------------");

        /**
         * list 中的 null 元素可能导致空指针异常(NullPointerException)
         */
        try {
            integerList.stream().forEach(integer -> {
                System.out.println(integer.toString());
                // TODO to do something

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("---------------- 分割线 ----------------------");

        /**
         * java 8 针对 list 集合 null 元素解决方案
         */
        /**
         * 1) 操作原来的 list
         */
        integerList.stream().filter(Objects::nonNull).forEach(integer -> {
            System.out.println(integer.toString());
            // TODO to do something

        });

        System.out.println("---------------- 分割线 ----------------------");

        /**
         * 2) 引用原来的 list
         */
        try {
            String listJsonStr = new ObjectMapper().writeValueAsString(integerList.stream().filter(Objects::nonNull).peek(integer -> {
                System.out.println(integer.toString());
                // TODO to do something

            }).collect(Collectors.toList()));
            System.out.println("listJsonStr: " + listJsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("---------------- 分割线 ----------------------");

        /**
         * 3) 生成一个新的 list(原来的 list 可能会产生内存垃圾)
         */
        List<Integer> newIntegerList = integerList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println("newIntegerList size: " + newIntegerList.size());
        // TODO to do something

    }
}
```














