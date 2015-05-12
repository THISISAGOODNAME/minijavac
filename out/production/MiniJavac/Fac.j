.class public Fac
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

.method public ComputeFac(I)I
.limit locals 3
.limit stack 17
iload 1
iconst_1
if_icmplt Label1
iconst_0
goto Label2
Label1:
iconst_1
Label2:
ldc 1
isub
ifeq Label3
goto Label5
Label3:
iconst_1
istore 2
goto Label4
Label5:
iload 1
aload_0
iload 1
iconst_1
isub
invokevirtual Fac/ComputeFac(I)I
imul
istore 2
Label4:
iload 2
ireturn
.end method

