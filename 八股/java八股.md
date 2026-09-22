# JAVA基础

## java特点

也是三大优势

- **平台无关性**：java“编写一次，运行无处不在”。Java编译器将源代码编译成字节码，该字节码文件可以在任何安装了Java虚拟机（JVM）的基础上运行。（JVM是用c/c++开发的）

  ​	字节码不能直接运行，必须通过JVM翻译成机器码才能运行。不同平台下编译生成的字节码是一样的，但是有JVM翻译成的机器码是不一样的。

- **面向对象**；

- **内存管理**：java有自己的垃圾回收机制，自动管理内存和回收不再使用的对象。这样开发者就不需要手动管理内存，从而减少**内存泄露**和其他内存相关的问题。

劣势：

- 性能较差，虽然JVM优化了许多，但是相比C++或者Rust这中原生编译语言，还是有一定开销。特别是启动时间，微服务场景下不如go之类的快。
- 语法繁琐，样板代码多，虽然有了lambda，但比起py还是不够简洁。
- 内存消耗：JVM本身占内存，对资源有限的环境可能不太好。
- 面向对象过于严格：有时候写简单程序反而麻烦，虽然java8引入了函数式编程，但不如其他语言自然。
- 开发效率，相比动态语言如python，java需要更多代码，编译过程也可能拖慢开发节奏。

## 为什么Java解释和编译都有

先看“**编译型**”的部分。写一份代码Test.java，不会直接交给 JVM 一行一行执行，而是先经过 Java 编译器 `javac`：

```
Test.java
   ↓ javac
Test.class
```

`Test.class` 里面保存的是 **Java 字节码**。

所以 Java 明显有一个“**编译阶段**”。

但是它又和 C/C++ 不完全一样。C/C++ 通常是：

```
C/C++源码
   ↓ 编译
本机机器码
   ↓
CPU直接执行
```

例如 Windows x86 编译出来的程序，本质上已经针对具体机器平台生成机器指令了。

Java 则是：Java源码 --> 字节码

这个字节码不是 CPU 可以直接执行的机器码，而是给 **JVM** 执行的。

JVM 拿到字节码以后，一种最基本的方式就是**解释执行**。可以粗略理解为：

```
读取一条字节码
↓
分析这条指令是什么意思
↓
执行

再读取下一条
↓
分析
↓
执行
```

比如字节码里有：

```
iadd
```

意思类似于：两个整数相加。

JVM 的解释器看到 `iadd`，就执行相应操作。

所以 Java 又具有**解释执行**的特点。

**Java 先编译成字节码，运行时 JVM 又可以解释字节码，并把热点代码即时编译成机器码。**

## 编译型语言和解释型语言的区别

- 编译型语言：在程序执行之前，整个源代码会被编译成目标平台的机器码（注：Java这种“编译为字节码再由虚拟机解释/JIT执行”的模式严格说属于编译型与解释型的混合），生成可执行文件。执行时直接运行编译后的代码，速度快，但跨平台性较差。
- 解释性语言：在程序执行时，逐行解释执行源代码，不生成独立的可执行文件。通常由解释器动态解释并执行代码，跨平台性好，但执行速度相对较慢。
- 典型的编译型语言如c/c++，典型的解释型语言如Python、JavaScript

## JVM是什么

jvm是java虚拟机，主要工作是解释字节码并映射到本地的CPU指令集和OS的系统调用。

JVM 提供了一层抽象，让上层 Java 程序不必直接面对不同操作系统的实现差异。Java程序只需要生成在Java虚拟机上运行的目标代码（字节码），就可以在多种平台上不加修改的运行，这也是Java能够“一次编译，到处运行的”原因。

## JVM、JDK、JRE三者关系

- JVM是java虚拟机，是java程序运行的环境，负责将java字节码（由java编译器生成）解释或编译成机器码，并执行程序。JVM提供了内存管理、垃圾回收、安全性等功能，是的Java程序具备跨平台性。

- JRE（Java Runtime Environment）是Java运行时环境，它包含了JVM和一组JAVA类库，不包含开发工具，只提供Java程序运行所需的最小环境

- JDK（Java Development Kit）是java开发程序包，是开发Java程序所需的工具集合，它包含了JVM、编译器（javac）、调试器（jdb）等开发工具，以及一系列的类库（如Java标准库和开发工具库）。JDK提供了开发、编译、调试和运行java程序所需的全部环境工具和环境。

  ![78980572145](C:\Users\Administrator\Desktop\study\八股\images\1789805721451.png)

## JVM和Java的区别

java是语言，jvm是平台

# 数据类型

## 数据类型转换方式

![78991091773](C:\Users\Administrator\Desktop\study\八股\images\1789910917737.png)

- 自动类型转换（隐式）：当目标类型的范围大于源类型时，java会自动将源类型转换为目标类型，不需要显式的类型转换。例如，将int转换为long、将float转换为double等。
- 强制类型转换（显示）
- 字符串转换：Java提供了将字符串表示的数据转换为其他类型数据的方法。例如，将字符串转换为整型int，可以使用int x = Integer.parseInt("123")；将字符串转换为浮点型double，可以使用Double.parseDouble()方法等。
- 数值之间的转换：Java提供了一些数值类型之间的转换方法，如将整型转换为字符型、将字符型转换为整型等。这些转换方式可以通过类型的包装类来实现，例如Character类、Integer类等提供了相应的转换方法。

## 计算机怎么存储浮点数

一个浮点数大致被拆成三部分：符号位 + 指数位 + 尾数位

| 类型     | 符号位 | 指数位 | 尾数位 | 偏移量 |
| -------- | ------ | ------ | ------ | ------ |
| `float`  | 1 bit  | 8 bit  | 23 bit | 127    |
| `double` | 1 bit  | 11 bit | 52 bit | 1023   |

(-1)^符号位 × 1.尾数 × 2^(指数 - 偏移量)

- 如5.75 = 101.11₂ = 1.0111₂ × 2²；符号位0（正），指数2（存2+127），尾数部分1.0111，只存0111，后面补0

  最终0 | 10000001 | 01110000000000000000000

## 类型互换的问题

- 大范围数据给小范围数据造成数据溢出，丢弃高位字节
- double转float，double转int发生精度损失

## 为什么用bigDecimal不用double

double会出现精度丢失的问题，double执行的是二进制浮点运算，二进制不能准确度表示所有小数。

```
System.out.println(0.05 + 0.01);
System.out.println(1.0 - 0.42);
System.out.println(4.015 * 100);
System.out.println(123.3 / 100);
输出：
0.060000000000000005
0.5800000000000001
401.49999999999994
1.2329999999999999
```

`BigDecimal`可以确保精确的十进制数值计算，避免了使用`double`可能出现的舍入误差。在创建BigDecimal对象时，应该使用字符串作为参数，而不是直接使用浮点数值，以避免浮点数精度丢失。

## 装箱和拆箱是什么

是将基本数据类型和对应的包装类之间进行转换的过程。

```
Integer i = 10;  //装箱
int n = i;   //拆箱
```

自动装箱主要发生在两种情况，一种是赋值时，另一种是在方法调用的时候。

- 赋值时，在Java 1.5以前我们需要手动地进行转换才行，而现在所有的转换都是由编译器来完成。
- 方法调用时，可以传入原始数据值或者对象，编译器会帮我们进行转换

自动装箱的弊端

​	在一个循环中进行自动装箱操作的情况，如下面的例子就会创建多余的对象，影响程序的性能。

```
Integer sum = 0; 
for(int i=1000; i<5000; i++){
	sum+=i; } 
```

上面的代码sum+=i可以看成sum = sum + i，但是+这个操作符不适用于Integer对象，首先sum进行自动拆箱操作，进行数值相加操作，最后发生自动装箱操作转换成Integer对象。其内部变化如下

```
int result = sum.intValue() + i; 
Integer sum = Integer.valueOf(result); 
```

由于我们这里声明的 sum 为 Integer 类型，自动装箱实际上由编译器替换为 Integer.valueOf(...) 调用，命中 IntegerCache（默认 -128~127）时会复用缓存对象，但本例中循环值都已超出缓存范围，因此会创建将近 4000 个 Integer 对象，降低程序性能并加重 GC 负担。因此在编程时需要注意：正确声明变量类型，避免因为自动装箱引起的性能问题（另外，new Integer(int) 自 JDK 9 起已被 @Deprecated，应统一使用 Integer.valueOf(int) 或直接自动装箱）。

## 为什么要有包装类

因为基本类型不是对象，但java的很多机制只面向对象。包装成对象可以把数据跟处理这些数据的方法封装在一起，如Integer的parseInt()；

- 泛型中的应用：Java泛型只能使用引用类型，不能用基本类型。所以集合只能存对象。如List<Integer>。
- null的需要：有的场景需要null而不是0；
- 包装类本身提供很多工具方法
- 很多API接受的是Object

## Integer缓存

JVM提前创建好一批常用的Integer对象，以后遇到这些数值时直接复用，而不是每次都创建新对象。

默认情况下范围是-128-127，当通过Integer.valueOf(int)方法创建一个在这个范围内的整数对象时，并不会每次都生成新的对象实例，而是复用缓存中的现有对象，会直接从内存中取出，不需要新建一个对象。

## HashMap实现原理

JDK1.7之前，HashMap数据结构是数组和数组，HashMap通过哈希算法将元素的Key映射到数组的槽位（Bucket）。如果多个key映射到同一槽位，他们会一链表的形式存储在同一个槽位上，因为链表的查询时间是O(n)，所以冲突很严重，一个索引上的链表非常长，效率就很低了。

JDK1.8时做了优化：当某个桶的链表长度≥8（TREEIFY_THRESHOLD）且哈希表数组长度≥64（MIN_TREEIFY_CAPACITY）时，会把链表转换为**红黑树**，把该桶的查找时间复杂度从O(n)降到O(log n)；如果数组长度<64，则只会触发扩容，不会立刻树化。相反的，再resize()过程中，若某个桶的节点数≤6（UNTREEIFY_THRESHOLD），红黑树会被退化为链表。

## HashMap链表发生转换后为什么不用二叉平衡树？

什么是红黑树？红黑树是一种**自平衡二叉查找树**。是为了解决二叉查找树的缺陷。二叉查找树就是有序的二叉树（左小右大）。如果升序/倒序，**二叉查找树会退化为链表结构**。查找性能会大大降低，时间复杂度会从从O(log n)降到O(n)。

红黑树特点：

- 每个节点要么是黑色，要么是红色。
- 根节点是黑色
- 每个叶子节点（NIL）是黑色
- **从根节点到叶子节点的任何一个路径上，不能出现两个连续的红节点。**
- **从根节点到叶子节点， 任何一条路径上都包含数量相同的黑节点。**

红黑树的平衡是通过**旋转和变色达到自平衡**的。

红黑树的**插入操作**，默认节点是红色。

![78944832954](C:\Users\Administrator\Desktop\study\八股\images\1789448329544.png)

- 插入节点的父节点为黑色，直接插入。
- 插入节点的父节点为红色：


# 面向对象

把事物抽象成类，把具体事物实例化为对象，对象具有的属性称为字段，行为称为方法。通过封装保护数据，通过继承复用代码，通过多态让程序更灵活。其核心价值是让代码更接近人的思维方式，从而更好地应对复杂软件的开发和维护。

## 继承

是一种可以使得子类自动共享父类数据结构和方法的机制。它是代码复用的重要手段，可以建立类与类之间的层次关系，使得结构更加清晰。

protected：**同一个包内可以访问，不同包的子类也可以访问**。

default：**只有同一个包内的类能访问，包外一律不行**，不管你是不是子类。

## 封装

对象代表什么，就得封装对应的数据，并提供数据对应的行为。对外隐藏对象的内部细节，仅通过对象提供的接口与外界交互。增强安全性和简化编程，使得对象更独立，

## 多态

**同一个行为，在不同对象身上表现出不同的形态**。

定义方法时，使用父类型作为参数，可以接收所有子类对象，体现多态的扩展性与便利性。

​	简单说就是**父类引用指向子类对象，调用方法时执行的是子类的实现**。

多态性可以分为编译时多态（重载）和运行时多态（重写）

多态体现在

- 方法重载：是指同一个类中有多个同名方法的实现，但是传入参数不同，编译器会在在编译时确定调用哪个方法。

- **方法重写：**指子类能够提供父类中同名方法的具体实现，在运行时，JVM回根据对象的实际类型，确定调用哪个版本的方法。这是实现多态的主要方式。 

- **接口与实现：**多个类可以实现同一个接口，并且用接口类型的引用（你可以把“引用”简单理解成“**指向对象的变量**”。例如：Dog d = new Dog(); d` 是 `Dog 类型的引用。而：Animal a = new Dog(); a` 是 `Animal` 接口类型的引用。）来调用这些类的方法。使得程序在面对不同实例时保持一贯的调用方式。

- **向上转型和向下转型：**

  | 转型     | 例子                    | 是否需要强制转换 | 安全性     |
  | -------- | ----------------------- | ---------------- | ---------- |
  | 向上转型 | `Animal a = new Dog();` | 不需要           | 通常安全   |
  | 向下转型 | `Dog d = (Dog) a;`      | 需要             | 可能有异常 |

## 抽象类和普通类的区别

**抽象类：**把多个子类共有的属性和行为放到父类中，同时把“必须有、但实现方式不同”的行为定义成抽象方法。

- 实例化：抽象类不能被实例化，只能被继承。
- 方法实现：普通类中的方法要有具体实现，抽象类可以有，也可以没有（没有的就是抽象方法）。
- 继承：继承抽象类的子类必须实现抽象方法，否则也要声明成抽象类

## 抽象类和接口的区别

他们都不能实例化，都定义了一些必须需要后续实现的功能。

- 接口中不能有具体方法，抽象类中可以（java8后可以定义default方法体）；接口支持多实现，抽象类只能单继承；抽象类可以包含实例变量和静态变量（static），而接口只能包含静态常量（public static final）
- 接口通常出现在设计初期，是知道行为，但是不知道具体的实现方式，就先把这些行为定义为接口，属于自上而下的设计。
- 抽象类一般出现在系统运行了一段时间以后，由于多个类的逻辑高度相似，于是把公共父类抽取出来，做成一个父类，让子类复用。属于自下而上的设计。

## 抽象类能加final修饰吗？

不能，抽象类是用来被继承的，而final修饰符用于禁止类被继承或方法被重写，因此，抽象类和final修饰符互斥。

## 非静态内部类和静态内部类的区别？

- 非静态内部类依赖于外部类的实例，而静态内部类不依赖与外部类的实例。
- 非静态内部类可以直接访问外部类的所有成员（包括实例变量和方法）；静态内部类可以直接访问外部类的静态成员，访问外部类的实例成员则必须通过外部类的实例引用。
- 非静态内部类不能定义静态成员（Java 16 之前），而静态内部类可以定义静态成员。

## 非静态内部类可以直接访问外部方法

非静态内部类可以直接访问外部方法是因为编译器在生成字节码时会为非静态内部类维护一个指向外部实例的引用。

这个引用是的非静态内部类能够访问外部类的实例变量和方法。编译器会在生成非静态内部类的构造方法时，将外部类实例作为参数传入，并在内部类的实例化过程中建立外部类实例与内部类实例之间的联系，从而实现直接访问外部方法的功能。

# 关键字

## java中final的作用是什么

主要有三个作用，修饰类、方法和变量

- 修饰类：表示这个类不能被继承，保证类的不可变性和安全性。
- 修饰方法：表示这个方法不能在子类中被重写。比如java.lang.Object类中的getClass方法，因为这个方法的行为是由java虚拟机底层实现来保证的，不应该被子类修改。
- 修饰变量：表示该变量不能再被重新赋值，否则会导致编译错误。但是，对于引用数据类型，final修饰意味着这个引用变量不能再指向其他对象，但对象本身的内容是可以改变的。

## static的作用是什么

static关键字主要是用于修饰类的成员（变量、方法、代码块）和内部类，其核心作用是将成员与类本身关联，而非与类的实例（对象）关联。

- 修饰变量：staic修饰的变量属于类本身，而非类的具体实例。所有对象共享一份静态变量，内存中只存在一份副本。可以通过类名直接访问，也可通过实例访问（不推荐）
- 修饰方法：静态方法属于类，但不属于任何实例，因此不能直接访问类中的非静态成员（变量/方法，因为非静态成员依赖实例对象存在），但可以访问静态成员。
- 修饰代码块：静态代码块在类初始化阶段（即执行<client>时），且执行一次（优于对象构造方法），用于初始化静态变量或执行类级别的预处理操作。JVM的类生命周期为：加载 → 链接（验证、准备、解析）→ 初始化，静态代码块属于"初始化"阶段而非"加载"阶段。
- 修饰内部类：转为静态内部类，不依赖于外部类实例，可以独立存在，

# 深拷贝和浅拷贝

## 深拷贝和浅拷贝的区别

- **浅拷贝**是指复制对象本身和其内部的值类型字段，但不会复制对象内部的引用类型字段。即只是创建一个对象，然后把原对象的字段复制到新对象中，包括引用类型的字段，所以两个对象指向的是同一个引用对象（共享引用）。
- **深拷贝**是指在复制对象的同时，将对象内部的所有引用类型字段的内容也复制一份，而不是共享引用。即深拷贝会递归复制对象内部所有的引用类型字段，最终生成一个新的对象，其内部所有对象都是新的。

## 实现深拷贝的三种方式

- 一、拷贝构造方法

  ```
  class Address {
      String city;

      Address(String city) {
          this.city = city;
      }

      Address(Address other) {
          this.city = other.city;
      }
  }
  ```

  ​

  ```
  class Person {
      String name;
      Address address;
      
      Person(String name, Address address) {
          this.name = name;
          this.address = address;
      }

      // 深拷贝构造方法
      Person(Person other) {
          this.name = other.name;
          this.address = new Address(other.address);
      }
  }
  ```

  使用：

  ```
  Person p1 =
      new Person("小明", new Address("北京"));

  Person p2 = new Person(p1);
  ```

- 二、实现 Cloneable 接口并重写 clone() 方法

  这种方法要求对象及其所有引用类型字段都是先Cloneable接口，并且重写clone()方法。在clone()方法中，通过递归克隆引用类型字段来实现深拷贝。

  比如：

  ```
  class Address implements Cloneable {
      String city;

      @Override
      protected Address clone() throws CloneNotSupportedException {
          return (Address) super.clone();
      }
  }
  ```

  然后 `Person`：

  ```
  class Person implements Cloneable {
      String name;
      Address address;
      
      @Override
      protected Person clone() throws CloneNotSupportedException {
          Person copy = (Person) super.clone();
          copy.address = this.address.clone();
          return copy;
      }
  }
  ```


- 三、使用序列化和反序列化

  通过将对象序列化为字节流，再从字节流反序列化为对象来实现深拷贝。要求所有对象及其引用类型字段都实现Serializable 接口


# 泛型

他允许类、接口和方法在定义时使用一个或多个类型参数，这些类型参数在使用时可以被指定为具体的类型。

- 适用于多种数据类型执行相同的代码。如果没有泛型，要实现不同类型的加法，每种类型都需要重载，通过泛型，可以复用为一个方法。
- 泛型中的类型在使用时指定，他将提供类型的约束，提供编译前的检查，用来保证类型安全。

# 对象

## java创建对象有哪些方式？

- 1使用new关键字：通过调用类的构造器来实例化对象。
- 2使用Class类的newInstance()方法：通过java的反射API，在运行时动态的创建对象。这种方式不需要在编译时知道具体的类。应用场景：框架设计(Spring的IOC容器)，动态代理

```
先定义统一接口：
interface Pay {
    void pay();
}

两个实现类：
class AliPay implements Pay {
    @Override
    public void pay() {
        System.out.println("使用支付宝支付");
    }
}
class WeChatPay implements Pay {
    @Override
    public void pay() {
        System.out.println("使用微信支付");
    }
}

String className = 从配置文件读取;
Pay pay = (Pay) Class.forName(className)
                     .getDeclaredConstructor()
                     .newInstance();
```

-  3使用clone()方法：通过实现Cloneable接口并重写Object类的clone()方法，可以基于一个现有对象（原型）创建一个新的副本对象
- 4使用反序列化：通过ObjectInputStream从一个字节流（通常是文件或网络）中重建一个对象。特点是不会调用构造器，类必须实现java.io.Serializable接口。

```
import java.io.*;

// 必须实现 Serializable 接口
public class Person implements Serializable {
    private String name;
    // ... 构造器和其他方法 ...
}

public class Main {
    public static void main(String[] args) {
        Person personToSave = new Person("David");
        
    // 序列化对象到文件
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"))) {
        oos.writeObject(personToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    // 从文件反序列化对象
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))) {
        Person restoredPerson = (Person) ois.readObject(); // 创建新对象
        restoredPerson.sayHello(); // 输出: Hello, David
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

- 5使用工厂模式：这是一种设计模式，不直接使用new，而是通过一个方法来返回对象实例，getInstance()和valueOf()都是常见的工厂方法。Java 标准库中的例子：Integer.valueOf(int)，Calendar.getInstance()。

## New出的对象什么时候回收

通过关键字new创建的对象，由Java的垃圾回收器（Garbage Collector）负责回收。垃圾回收器的工作是在程序运行过程中自动进行的，他会周期性的检测不再被引用的对象，并将其回收释放内存。

具体的，回收时机是由GC根据一下机制来判断的

- 可达性分析算法
- 终结器

## 如何获取私有对象

- 公共的getter方法
- 反射机制：允许在允许时检查和修改类、方法、字段等信息，通过反射可以绕过private访问修饰符的限制来获取私有对象。

# 异常

异常就是代表程序出现的问题，异常的时候会创建一个异常对象。

​		Java.lang.Throwable!()

Error						      Exception

​				RuntimeException			其他异常

## Error：

代表的系统级别错误（属于严重问题），是给sun公司自己用的，开发人员不用管

## Exception：

叫做异常，代表程序可能出现的问题。我们通常会用Exception以及它的子类来封装程序出现的问题。

## 运行时异常：

RuntimeException及其子类，编译阶段不会出现异常提醒，运行时出现的异常（数组索引越界异常）。包括RuntimeException本身和其子类。**一般是由于参数传递错误带来的错误**。

## 编译时异常：

编译阶段就会出现异常提醒的。（如：日期解析异常），没有继承RuntimeException的异常，直接继承于Exception。编译阶段就会错误提示，运行时出现的。

## 编译阶段：

java不会运行代码，只会检查语法是否错误，或者做一些性能的优化

为什么不全部作为运行时异常呢，编译时期异常，更多是**提醒程序员检查本地信息。**

## 异常的作用

一：异常时用来查询bug的关键参考信息。

二：异常可以作为方法内部的一种**特殊返回值**。以便通知调用者底层的执行情况。

## 异常的处理方式

### **1JVM默认的处理方式**

​	把异常的名称，异常的原因，以及异常出现的位置等信息输出在了控制台。

​	程序停止执行，下面的代码不会执行

### **2自己处理（捕获异常）**

​	try

​		{可能出现异常的代码;}

​	catch (异常类名, 变量名)

​		{异常的处理代码;}

​	后面的代码正常执行。

灵魂四问：

​	1如果try中没有遇到问题，怎么执行？	**跳过catch中的代码。**

​	2如果try中遇到多个问题，怎么执行？	**首先，不会同时捕获多个异常，其次，要写多个catch与之对应。细节：如果要捕获多个异常，且这些异常中存在父子关系，那么父类一定要写在下面；在JDK7之后，我们可以在catch中同时捕获多个异常，中间用|进行隔开。**

​	3如果try中遇到的问题没有被捕获，怎么执行？	**按照JVM默认的处理方式。**

​	4try中遇到了问题，try下面的代码还会执行吗？	**不会。**

### **3抛出异常**

throws

​	写在方法定义出，表示声明一个异常，告诉调用者，使用本方法可能会有哪些异常

​	编译时期异常必须要写，运行时异常可以不写

throw

​	写在方法内的，结束方法

​	手动抛出异常对象，交给调用者

​	**方法中下面的代码就不再执行了**



## 异常中的常见方法

Throwable的成员方法

**getMessage()返回此throwable的详细消息字符串**

**toString()返回此可抛出的简短描述**

**printStackTrace()把异常的错误信息输出在控制台**

# 多线程

线程

​	线程是操作系统能够进行运算调度的最小单位。他被包含在进程之中，是进程中的实际运作单位。 

并发

​	在同一时刻，有多个指令在单个cpu上交替执行

并行

​	在同一时刻，有多个指令在多个cpu上同时执行。（虽然只有一个cpu，但是cpu是多核多线程的，2核4线程就是可以同时执行4个线程）

## 多线程的实现方式

1继承Thread类的实现方式

```
* 1多线程的第一种启动方式自定定义一个类，继承Thread
* 2重写run方法
* 3创建子类的对象，并启动线程（start方法）
```

2实现Runnable接口的方式进行实现的

```
* 1多线程的第二种启动方式：自己实现Runnable接口
* 2重写里面的run方法
* 3创建自己的类的对象
* 4创建一个thread类的对象，并开启线程

由于只会创建以此MyRunnable的对象，所以MyRunnable中的成员变量不用加static关键字了，非常适合多个相同线程来处理同一份资源的情况。
```

3利用Callable接口和Future接口方式实现

```
*   特点：可以获取到多线程运行的结果
* 1.创建一个类Mycallable实现Callable接口
* 2.重写call方法（是有返回值的，表示多线程运行的结果）
* 3.创建MyCallable的对象(表示多线程要执行的任务)     			 MyCallable3 mc = new MyCallable3();
* 4.创建Future的对象（作用管理多线程运行的结果，泛型是结果的类型）	FutureTask<Integer> ft = new FutureTask<>(mc);
* 5.创建Thread类的对象，并启动（表示线程）						Thread t1 = new Thread(ft);
```

4.采用[线程池](#线程池)

![78843999549](C:\Users\Administrator\Desktop\study\八股\images\1788439995491.png)

## 常见的成员方法

![78851435185](C:\Users\Administrator\Desktop\study\八股\images\1788514351850.png)

**getName()	返回此线程的名字**

​	如果没有给线程设置名字，也是有默认的名字的，格式Thread-X

**currentThread()	获取当前线程的对象**

​	细节：当JVM虚拟机启动之后，会自动的启动多条线程，其中有一条线程叫做main，作用是调用main方法

**sleep()	让线程休眠指定的时间，单位为毫秒**

​	细节：哪条线程执行到这个方法，哪条线程停留指定的时间

java采用的是抢占式调度。优先级最小是1，最大是10，默认是5。

**setDaemon(boolean on)		设置守护线程。**

​	细节：当其他的非守护线程执行完毕之后，守护线程会陆续结束。

​	当非守护线程结束后，那么守护线程也没有存在的必要了，即**后续的代码就不需要执行了**。

​	例子：

​		聊天：线程1

​		传输文件：线程2

​	如果把聊天框关闭了，那么传输文件也会中断，没有继续执行的必要了。

**yield()	出让线程/礼让线程，让线程的尽可能均匀一些**

**join()	插队线程，把t这个线程，插入到当前线程之前。**

## 线程的生命周期

![78852125195](C:\Users\Administrator\Desktop\study\八股\images\1788521251956.png)

有执行资格，没有执行权（就绪状态，有资格去抢cpu的执行权，现在还没有抢到，不能执行代码）

详细见[线程状态](#线程的状态)

### 线程的状态

![78859581647](C:\Users\Administrator\Desktop\study\八股\images\1788595816471.png)

jvm中，没有“运行”这个状态，因为线程抢到cpu执行权时，jvm就会把当前的线程交给操作系统去管理了，所以没有运行状态。可以调用线程Thread中的getState()方法获取当前线程的状态。

NEW：尚未启动的线程状态，即线程创建，还未调用start方法

RUNNABLE：就绪状态（调用start，等待调度）+正在运行

BLOCKED：等待监视器锁时，陷入阻塞状态

WAITING：等待状态的线程正在等待另一线程执行特定的操作（如notify）

TIMED_WAITING：具有指定等待时间的等待状态

TERMINATED：线程完成执行，终止状态

## 线程的安全问题

线程在执行代码时。cpu的执行权随时可能被抢走。  

StringBuilder和StringBuffer类内方法几乎一致，但是StringBuffer是线程安全的，因为其方法加了synchronized关键字。

### java线程安全

在三个方面体现

​	原子性：提供互斥访问，同一时刻只能有一个线程对数据进行操作，在Java中使用了atomic包（这个包提供了一些支持原子操作的类，这些类可以在多线程环境下保证操作的原子性）和synchronized关键字来确保原子性；

​	可见性：一个线程对主内存的修改可以及时地被其他线程看到，在Java中使用了synchronized和volatile这两个关键字确保可见性；

​	有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排序，该观察结果一般杂乱无序，在Java中使用了[happens-before](#happens-before)原则来确保有序性。



### **同步代码块：用synchronized(锁对象){ }包裹**

​	同步代码块的两个小细节：1 synchronized不能包裹while(true)循环；2 synchronized(锁对象)中的锁对象一定要是唯一的，要用static关键字，一般是用**当前类的字节码文件（类名.class）**。

### 同步方法：把synchronized关键字加到方法上

​	把同步代码块抽取成同步方法。

​	特点1 同步方法是所致方法里的所有代码块；

​	特点2 锁对象不能自己指定；如果当前方法是非静态的，是this；如果是静态的，是当前类的字节码文件对象

### Lock锁：手动上锁，手动释放锁

Lock是接口，不能实例化，需要采用实现类ReentranLock来实例化。

###  死锁：发生了 锁嵌套

​	代码级别的4个必要条件（全部满足才会死锁）

1. **互斥**：资源同一时间只能被一个线程占用
2. **持有并等待**：线程拿着已有的资源，还去申请新的资源
3. **不可抢占**：已分配的资源不能被强行抢走
4. **循环等待**：A等B，B等A，形成环路

### 生产者和消费者（等待唤醒机制）

打破随机，**实现线程轮流交替执行的效果**

线程A：生产者，生产数据

线程B：消费者，消费数据

或者使用ArrayBlockingQueue阻塞队列put() take()方法



### volatile关键字

volatile保证不同线程对共享变量操作的可见性，也就是说一个线程修改了volatile修饰的变量，当修改写回主内存时，另外一个线程立即看到最新的值。

​      但是volatile不保证原子性。

### CAS & Synchronized

CAS(Compare And Swap)

Synchronized是从悲观的角度出发：

​	总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁（**共享资源每次只给一个线程使用，其它线程阻塞，用完后再把资源转让给其它线程**）。因此Synchronized我们也将其称之为悲观锁。jdk中的ReentrantLock也是一种悲观锁。

CAS是从乐观的角度出发:

​	总是假设最好的情况，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据。CAS这种机制我们也可以将其称之为乐观锁。

## 线程的通信

**共享变量**

​	多个线程可以访问和修改同一个共享变量，从而实现信息的传递。为了保证线程安全，通常需要使用 synchronized 关键字或 **volatile 关键字**。

**wait()、notify()和notifyAll()**

​	wait() 方法使当前线程进入等待状态，notify() 方法唤醒在此对象监视器上等待的单个线程，notifyAll() 方法唤醒在此对象监视器上等待的所有线程。（必须写在synchronized内）

**Lock锁**

​	提供和wait()、notify()和notifyAll()类似的方法。

**阻塞队列**

​	java.util.concurrent 包中的 BlockingQueue 接口提供了线程安全的队列操作，当队列满时，插入元素的线程会被阻塞；当队列为空时，获取元素的线程会被阻塞。



## 数据一致性

**事务管理：**使用数据库事务来确保一组数据库操作要么全部成功提交，要么全部失败回滚。通过ACID（原子性、一致性、隔离性、持久性）属性，数据库事务可以保证数据的一致性。

**锁机制：**使用锁来实现对共享资源的互斥访问。在 Java 中，可以使用 synchronized 关键字、ReentrantLock 或其他锁机制来控制并发访问，从而避免并发操作导致数据不一致。

**版本控制：**通过乐观锁的方式，在更新数据时记录数据的版本信息，从而避免同时对同一数据进行修改，进而保证数据的一致性。

# 线程池

**核心原理**

1 创建一个池子，池子中是空的

2 提交任务时，池子会创建新的线程对象，任务执行完毕，线程归还给池子下回再次提交任务时，不需要创建新的线程，直接复用已有的线程即可

3 但是如果提交任务时，池子中没有空闲线程，也无法创建新的线程，任务就会排队等待

**线程池代码实现**

Executors：线程池的工具类通过调用方法返回不同类型的线程池对象。

```
public static ExecutorService newCachedThreadPool();    创建一个没有上限的线程池
public static ExecutorService newFixdThreadPool();      创建有上限的线程池
```



### 自定义线程池

核心参数 

* ```
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
        corePoolSize, // 核心线程数 线程池长期维持的最小线程数
        corePoolSize * 2, // 最大线程数 线程池能容纳的最多线程数
    	60L, // 空闲线程存活时间 超过核心线程数的空闲线程 多久后销毁
      	TimeUnit.SECONDS, // 存活时间单位
        new ArrayBlockingQueue<>(100), // 任务阻塞队列 核心线程忙时 新任务存这里
        Executors.defaultThreadFactory(), // 线程创建工厂 用于设置线程名 优先级等
        new ThreadPoolExecutor.AbortPolicy() // 拒绝策略 队列满且线程数达最大时 如何处理新任务
     );
    ```

    ​

![78867088424](C:\Users\Administrator\Desktop\study\八股\images\1788670884246.png)

**核心线程都在忙且队伍排满了，才会创建临时线程** 

核心线程满了，阻塞队列满了，临时线程满了，会触发拒绝服务策略。

服务拒绝策略（是内部类，为什么？内部类是依赖外部类而存在的，单独出现没有意义，而且内部类又是一个独立的个体）：

​	ThreadPoolExectuor.AbortPolicy：默认策略：丢弃任务并抛出 RejectedExecutionException 异常

​	ThreadPoolExectuor.DiscardPolicy：丢弃任务不抛异常

​	ThreadPoolExectuor.DoscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入到队列

​	ThreadPoolExectuor.CallerRunsPolicy：调用任务的run方法绕过线程池直接执行

**线程池多大合适？**

​	最大并行数？8核16线程，就是16。

​	CPU密集型运算：最大并行数+1，防止页缺失故障或其他原因，导致线程暂停，额外的线程就可以顶上去，保证cpu的时钟周期不被浪费。

​	I/O密集型运算：最大并行数 * 期望cpu利用率 * 总时间（包括cpu计算时间 + 等待时间） / cpu计算时间

![78867388222](C:\Users\Administrator\Desktop\study\八股\images\1788673882220.png)

用thread dump进行测试：总时间（包括cpu计算时间 + 等待时间） / cpu计算时间



# 反射

**原理：**JVM 在类加载之后，会保存类的完整元数据；Java 反射就是在运行时通过 `Class` 等对象读取这些元数据，并动态操作类、字段、方法和构造器。

**反射**允许对封装类的**字段（成员变量）**，**方法**和**构造方法**的（所有）信息进行编程访问。

## 获取

从class字节码文件中获取的，所以先要学习怎么获取到class字节码文件对象

​	三种方式

​	1Class.forName("全类名"); 最常用

​	2类名.class; 当作参数进行传递

​	3对象.getClass(); 当已经有了这个类的对象时，才可以使用

### 利用反射获取构造方法

![78875929275](C:\Users\Administrator\Desktop\study\八股\images\1788759292755.png)

获取到class字节码文件对象clazz后，调用clazz.getConstructors()等方法获取到**构造方法对象**。

如果使用clazz.getDeclaredConstructors()获取到了private类型的构造方法对象con4，可以调用con4.setAccessible(true)来**取消临时校验**（**暴力反射**）

### 反射获取成员变量

![78875932172](C:\Users\Administrator\Desktop\study\八股\images\1788759321725.png)

clazz.getFields()

setAccessible(true)

### 反射获取成员方法

![78876195028](C:\Users\Administrator\Desktop\study\八股\images\1788761950289.png)

## 解剖

## 作用

1获取一个类里面所有的信息，获取到了之后，在执行其他的业务逻辑。

2结合配置文件，动态的创建对象并调用方法。

## 反射在平时写代码或者框架中的应用常见有哪些

- 加载数据库驱动：项目底层数据库有时是用mysql，有时用oracle，需要动态的根据实际情况加载驱动类[见创建对象的方式2](#java创建对象有哪些方式？)

- **Spring IOC / 依赖注入**

  Spring 启动时扫描 `@Component`、`@Service`、`@Controller` 等类，然后通过反射创建 Bean；看到 `@Autowired`、`@Resource` 时，再通过反射把依赖注入进去。

- **Spring MVC：为什么一个URL能自动找到你的方法**

  比如写

  ```
  @RestController
  @RequestMapping("/user")
  public class UserController {
  	@GetMapping("/{id}")
  	public User getUser(@PathVariable Long id) {
      	return userService.getById(id);
  	}
  }
  ```

  然后浏览器请求：GET /user/100，Spring 是怎么知道应该执行：getUser() 的？

  ​	spring启动时，会扫描Controller，反射获取类上的注解@RequestMapping("/user")，把 URL → 方法 的映射关系存进 Map，请求来了，DispatcherServlet拿URL去Map里查找，找到方法后用反射调用（Method.invoke()）

  ​

- 是待补充

# 注解

自定义一个注解：

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
    String name();//一个注解方法
    int age() default 18;//一个注解方法，带默认值
}
```

编译后，Java 编译器会把它变成一个**接口**：

```
// 反编译后大概长这样
public interface MyAnnotation extends java.lang.annotation.Annotation {
    String name();
    int age();
}
```

注意：**注解本质上是一个继承自 Annotation 的接口**，它的“方法”就是注解的属性。

```
你写代码：
  @MyAnnotation(name = "张三", age = 20)
         ↓
编译：
  注解信息写入 .class 文件的常量池（RuntimeVisibleAnnotations）
         ↓
JVM 加载类：
  解析常量池中的注解信息 → 生成 memberValues Map
         ↓
反射调用 getAnnotation()：
  创建 AnnotationInvocationHandler（持有 memberValues）
  创建 JDK 动态代理对象
         ↓
你调用 anno.name()：
	实际并不会执行某个真实的方法体，而是被 JDK 动态代理拦截，转交给AnnotationInvocationHandler.invoke() 处理，最终从 memberValues Map 中取值返回。
  代理拦截 → invoke() → memberValues.get("name") → 返回 "张三"
```

## 注解的原理

直接的本质是一个继承了Annotation的特殊接口，作用是**：给代码打“标签”，让框架或工具在编译期或运行期读取这些标签，从而自动完成某些逻辑。**

其具体实现类是Java运行时生成的动态代理类（反射调用时

```
MyAnnotation anno = clazz.getAnnotation(MyAnnotation.class);
```

拿到的**不是** `MyAnnotation` 的普通实现类实例，而是一个 **JDK 动态代理对象**。）。

调用自定义注解的方法时，会转发给AnnotationInvocationHandler，然后调用其中的invoke方法。该方法会从memberValues这个Map中索引出对应的值。（memberValues的来源是Java常量池）

## 注解解析的底层实现

注解的解析主要依赖于 Java 的反射机制。以下是解析注解的基本流程：
1、获取注册信息：通过反射 API 可以获取类、方法、字段等元素上的注解。例如：

```
Class<?> clazz = MyClass.class;
MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
if (annotation != null) {
    System.out.println(annotation.value());
}
```


2、底层原理：反射机制的核心类是 java.lang.reflect.AnnotatedElement，它是所有可以被注解修饰的元素（如 Class、Method、Field 等）的父接口。该接口提供了以下方法：

- getAnnotation(Class<T> annotationClass)：获取指定类型的注解。
- getAnnotations()：获取所有注解。
- isAnnotationPresent(Class<? extends Annotation> annotationClass)：判断是否包含指定注解。

这些方法本身由纯 Java 实现，并不是 native 方法。以 Class.getAnnotation(...) 为例，其内部会先触发注解数据的延迟解析：通过 AnnotationParser.parseAnnotations(...) 读取 JVM 在类加载阶段从 class 文件属性（RuntimeVisibleAnnotations 等）中抽取并传回的原始字节，反序列化成 Map<Class<? extends Annotation>, Annotation>，最终以动态代理对象（AnnotationInvocationHandler）的形式返回给调用方。

JVM 在类加载阶段确实会解析 .class 文件中的注解信息，但这部分工作对应的 native 接口位于底层的常量池与属性表读取，而不是 getAnnotation 这一层 API。

因此，注解解析的底层实现主要依赖于 Java 的反射机制和字节码文件的存储。通过 @Retention 元注解可以控制注解的保留策略，当使用 RetentionPolicy.RUNTIME 时，可以在运行时通过反射 API 来解析注解信息。在 JVM 层面，会从字节码文件中读取注解信息，并创建注解的代理对象来获取注解的属性值。

# Object

Java Object 类是所有类的超类，默认提供 11 个核心方法，核心用于对象比较、哈希、字符串表示、线程同步等。

两个注意点：

- equals 配套的必须重写 hashCode 方法，因为 Java 的约定是如果两个对象 equals 返回 true，它们的 hashCode 必须相等；如果 hashCode 不相等，equals 一定返回 false。如果只重写 equals 不重写 hashCode，会导致对象在 HashMap HashSet 等集合中无法正确存储，比如两个 id 相同的 User 对象，equals 返回 true，但 hashCode 不同，会被当成两个不同元素存入集合。
- finalize 方法，它是对象被垃圾回收器回收前会调用的方法，默认是空实现。但现在基本不推荐使用，因为它的执行时机不确定，可能很久才执行甚至不执行，而且可能导致对象复活，影响垃圾回收效率，Java9 之后已经标记为过时，替代方案是使用 try with resources 或者 PhantomReference 来处理资源释放。

## ==和equals有什么区别

==比较的是地址，equals比较的是内容

```
String a = new String("hello");
String b = new String("hello");
System.out.println(a == b);  // 输出 false
System.out.println(a.equals(b));  // 输出 true

陷阱：
String c = "hello";
String d = "hello";
System.out.println(c == d);  // 输出 true
直接用双引号创建字符串的时候，JVM 会把它扔到一个叫"字符串常量池"的地方。如果池子里已经有了 "hello"，那 d 就直接复用 c 指向的那个对象，所以它俩地址是一样的
```

## hashcode和equals的关系

equals为true，hashcode一定ture；hashcode为true，equals不一定true（hash碰撞）。

## String、StringBuffer、StringBuilder的区别和联系

| 特性         | String                         | StringBuilder    | StringBuffer     |
| ------------ | ------------------------------ | ---------------- | ---------------- |
| **不可变性** | 不可变（修改会生成一个新对象） | 可变             | 可变             |
| **线程安全** | 是（因不可变）                 | 否               | 是（同步方法）   |
| **性能**     | 低（频繁修改时）               | 高（单线程）     | 中（多线程安全） |
| **适用场景** | 静态字符串                     | 单线程动态字符串 | 多线程动态字符串 |

# Java新特性



# 动态代理

无侵入式地给代码增加额外功能

------

------

# 集合概念

------

------

# 内存模型

## jvm的内存模型介绍一下

JVM运行时内存共分为虚拟机栈、堆、方法区（后被元空间取代）、程序计数器、本地方法栈。

- 方法区（Method Area）

  JVM规范定义的一块线程共享的逻辑内存区域，主要用于存放“类级别的信息”。

  主要存储类元数据，字段信息，方法信息，方法字节码，运行时常量池等一些类级别相关信息。

  ​

- 堆

  堆是线程共享的运行时内存区域，存放对象实例，包括程序中创建的对象，以及java虚拟机自动创建的对象和数组。

  堆空间可以分为新生代和老年代，还包括持久代（JDK7及前)或元空间（JDK8及后）

  特点

  - 线程共享
  - 主要存放对象和数组
  - GC的主要管理区域
  - 容量通常最大
  - 可能发生OutOfMemoryError

- 栈

  存储一切和方法有关的（局部变量、方法调用的参数、方法返回地址以及一些临时数据），且存的一般是对象的引用，真正的对象实例通常在堆里。

  每个线程都用自己独立的虚拟机栈，方法调用一次，就会创建一个栈帧如栈；方法执行结束，对应栈帧出栈，

  栈帧包括：局部变量表，操作数栈，动态链接、方法出口等信息。StackOverflowError 和 OutOfMemoryError

- 本地方法栈

  与Java虚拟机栈类似，用于存储执行本地（Native）方法的数据。StackOverflowError 和 OutOfMemoryError

  ​

- 程序计数器（PC Register）

  每个线程都有一个程序计数器，用于记录当前线程正在执行的JVM字节码指令的位置，线程切换回来以后，能够从之前的位置继续执行。没有规定任何OOM情况。

------

------

# Spring

## IoC（Inversion of Control，控制反转）

​	把对象的创建权和管理权从程序员手中，反转给Spring容器。

​	开发者只需要定义好Bean及其依赖关系，Spring容器负责创建和组装这些对象。

```
//无IoC
public class UserService { 
    // 程序员自己控制对象的创建
    private UserDao userDao = new UserDao();     
    private LogService logService = new LogService();
    private MailService mailService = new MailService();
    // 问题：如果 UserDao 构造函数变了，这里也要改，牵一发动全身
}
//有IoC
@Service
public class UserService {
    @Autowired
    private UserDao userDao;      // 不用 new，容器注入
    @Autowired
    private LogService logService; // 容器注入
    // 好处：UserDao 怎么创建的，UserService 完全不用管，由容器负责
}
```

在底层做三件事：

| 步骤        | 做什么                                     | 技术手段                                           |
| ----------- | ------------------------------------------ | -------------------------------------------------- |
| **1. 扫描** | 找到所有带 `@Component`、`@Service` 等的类 | 类路径扫描（ClassPath Scanning）                   |
| **2. 创建** | 通过反射调用构造方法，创建对象实例         | Java 反射（`Class.newInstance()`）                 |
| **3. 存储** | 把对象存到一个大 Map 里（容器）            | `ConcurrentHashMap`，key 是 beanName，value 是对象 |

**Bean的生命周期（IoC的完整流程）**

```
1. 实例化（通过构造器 new）
2. 属性注入（@Autowired 赋值）
3. 初始化（@PostConstruct 或 afterPropertiesSet）
4. 使用（业务调用）
5. 销毁（@PreDestroy，容器关闭时清理）
```

**两种 IoC 容器（Spring 里的具体实现）**

| 容器                   | 特点                              | 使用场景                         |
| ---------------------- | --------------------------------- | -------------------------------- |
| **BeanFactory**        | 延迟实例化，用到时才创建          | 资源受限的环境（不常用）         |
| **ApplicationContext** | 非延迟，启动时就创建所有单例 Bean | 企业级应用（**日常用的就是它**） |

​	在企业级（web）应用里面,我们一般把比较耗时的事情放在系统启动的时候完成。

## DI（依赖注入）

**DI** 是 IoC 的具体实现方式，它的核心意思是：**当 A 对象需要 B 对象时，不是由 A 自己 new B，而是由外部容器把 B 注入给 A。**

| 方式            | 示例                                                         | 推荐度                           |
| --------------- | ------------------------------------------------------------ | -------------------------------- |
| **构造器注入**  | ` @Autowired public UserService(UserDao dao) { this.dao = dao; }` | ✅ **最推荐**（不可变，易测试）   |
| **Setter 注入** | `@Autowired public void setDao(UserDao dao)`                 | ⚠️ 可选                           |
| **字段注入**    | `@Autowired private UserDao dao;`                            | ❌ 虽然方便，但不推荐（难以测试） |

## AOP（面向切面编程）

作用：在**不惊动原始设计（Spring无侵入式编程）**的基础上为其做**功能增强**

原来设计的功能中的任意位置，叫**连接点**。

对于要追加功能的方法，叫切入点。切入点就是被通知追加功能的**连接点**，连接点 > 切入点。

对于抽取出来的，希望大家都有的功能（即希望增强的功能），叫**通知**。由于功能（方法）不能单独存在，需要依托 类，叫做**通知类**。

怎么把通知和切入点做绑定？中间的对应关系，叫**切面**。

| **日志记录** | 记录方法调用时间、参数、返回值 | `@Before` + `@AfterReturning` |
| ------------ | ------------------------------ | ----------------------------- |
| **权限校验** | 检查用户是否有权限执行该方法   | `@Before` 检查权限            |
| **事务管理** | 开启、提交、回滚事务           | `@Around` 环绕通知            |
| **性能监控** | 统计方法执行耗时               | `@Around` 计算时间差          |
| **缓存管理** | 先从缓存取，没有则查数据库     | `@Around` 控制缓存逻辑        |
| **异常处理** | 统一捕获异常并返回友好提示     | `@AfterThrowing`              |

Spring AOP支持两种动态代理：

- 基于JDK的动态代理：使用java.lang.reflect.Proxy类和java.lang.reflect.InvocationHandler接口实现。这种方式需要代理的类实现一个或多个接口。
- 基于CGLIB的动态代理：当被代理的类没有实现接口时，Spring会使用CGLIB库生成一个被代理类的子类作为代理。CGLIB（Code Generation Library）是一个第三方代码生成库，通过继承方式实现代理。

应用：

​	最常见的是事务管理，Spring 的声明式事务就是基于 AOP 实现的。我们只需要在方法上标注@Transactional，Spring 就会通过 AOP 在方法执行前开启事务，执行后根据是否有异常决定提交或回滚，不用手动写事务控制代码，大大简化了事务管理。

## IOC和AOP是通过什么机制来实现的？

Spring IOC 实现机制：

- 反射：Spring IOC容器利用Java的反射机制动态地加载类、创建对象实例及调用对象方法，反射允许在运行时检查类、方法、属性等信息，从而实现灵活的对象实例化和管理。
- 依赖注入：IOC的核心概念是依赖注入，即容器负责管理应用程序组件之间的依赖关系。Spring通过构造函数注入、属性注入或方法注入，将组件之间的依赖关系描述在配置文件中或使用注解。
- 设计模式 - 工厂模式：Spring IOC容器通常采用工厂模式来管理对象的创建和生命周期。容器作为工厂负责实例化Bean并管理它们的生命周期，将Bean的实例化过程交给容器来管理。
- 容器实现：Spring IOC容器是实现IOC的核心，通常使用BeanFactory或ApplicationContext来管理Bean。BeanFactory是IOC容器的基本形式，提供基本的IOC功能；ApplicationContext是BeanFactory的扩展，并提供更多企业级功能。

Spring AOP实现机制：

​	Spring AOP的实现依赖于动态代理技术。动态代理是在运行时动态生成代理对象，而不是在编译时。它允许开发者在运行时指定要代理的接口和行为，从而实现在不修改源码的情况下增强方法的功能。



# JMM

## happens-before

​	是 JMM 定义的一组可见性和有序性规则，告诉程序员：在什么条件下，一个操作的执行结果对另一个操作是可见的。它是判断多线程程序是否正确的重要依据。

| **程序次序规则**  | 一个线程内，书写在前面的代码 happens-before 书写在后面的代码 | `a = 1;` 执行在 `b = a;` 之前                                |
| ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **volatile 规则** | 对一个 `volatile` 变量的**写**操作，happens-before 于后续对这个变量的**读**操作 | 你写入 `volatile` 值，其他线程马上看到最新值                 |
| **锁规则**        | 对一个锁的**解锁**操作，happens-before 于后续对这个锁的**加锁**操作 | 线程 A 释放 `synchronized` 锁后，线程 B 获得同一把锁能看到 A 的修改 |