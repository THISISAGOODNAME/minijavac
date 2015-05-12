# minijavac
&#160; &#160; &#160; &#160;一个minijava语言的编译器

##功能说明
&#160; &#160; &#160; &#160;一个java语言写的minijava语言编译器，可以把.java文件编译成.class文件，并且可以被java1.2以上版本的jre运行(jre1.7/1.8测试通过)

##minijava语言说明

MiniJava is a subset of Java. The meaning of a MiniJava program is given by its meaning as a Java program. Overloading is not allowed in MiniJava. The MiniJava statement System.out.println( ... ); can only print integers. The MiniJava expression e.length only applies to expressions of type int [].

The Grammar link on the left has the formal BNF for MiniJava. You can also view some sample MiniJava programs under the Programs link. The Framework link provides a helpful interface to write a MiniJava Compiler. The software link has links to software and tools that may be helpful to write and test your compiler. The Java Reference is a helpful resource to learn the language. And the MiniJava Reference is a miniJava reference manual from the appendix of the book.

(C) MiniJava project by Joao Cangussu, Jens Palsberg and Vidyut Samanta.

&#160; &#160; &#160; &#160;详细见[http://www.cambridge.org/us/features/052182060X/](http://www.cambridge.org/us/features/052182060X/)，测试样例该网页上也有所提供

##运行说明

&#160; &#160; &#160; &#160;该程序用intellij idea 14开发，文件结构下的test.java是一个测试用的样例，可以自行替换。工程已经默认把命令行参数改成了test.java，请根据实际需要进行调整。
