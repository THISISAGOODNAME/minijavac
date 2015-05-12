.class public Factorial
.super java/lang/Object
.method public <init>()V
aload_0
invokespecial java/lang/Object/<init>()V
return
.end method

.method public static print(I)V
.limit locals 5
.limit stack 5
iload 0
getstatic java/lang/System/out Ljava/io/PrintStream;
swap
invokevirtual java/io/PrintStream/print(I)V
return
.end method

.method public static print(Ljava/lang/String;)V
.limit locals 5
.limit stack 5
aload 0
getstatic java/lang/System/out Ljava/io/PrintStream;
swap
invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
return
.end method

.method public static main([Ljava/lang/String;)V
.limit locals 1
.limit stack 16
new Fac
dup
invokespecial Fac/<init>()V
bipush 10
invokevirtual Fac/ComputeFac(I)I
invokestatic Factorial.print(I)V
ldc "\n"
invokestatic Factorial.print(Ljava/lang/String;)V
return
.end method

