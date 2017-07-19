1、try块中没有抛出异常，try、catch和finally块中都有return语句
public static int NoException(){
         int i=10;
         try{
           System.out.println("i in try block is："+i);
           return --i;
         }
         catch(Exception e){
           --i;
           System.out.println("i in catch - form try block is："+i);
           return --i;
         }
         finally{     
           System.out.println("i in finally - from try or catch block is："+i);
           return --i;
         }  
}
运行代码：
public static void main(String[] args) {
        System.out.println("=============NoException==================");
        System.out.println(NoException());
        System.out.println("===============================");   
}
运行结果：
=============NoException==================
i in try block is：10
i in finally - from try or catch block is：9
8
===============================

执行顺序：
   执行try块，执行到return语句时，先执行return的语句，--i，但是不返回到main方法，执行finally块，遇到finally块中的return语句，执行--i,并将值返回到main方法，这里就不会再回去返回try块中计算得到的值。
结论：try-catch-finally都有return语句时，没有异常时，返回值是finally中的return返回的。

2.try块中没有抛出异常，仅try和catch中有return语句
public static int NoException1(){
            int i=10;
            try{
                System.out.println("i in try block is："+i);
                return --i;
            }
            catch(Exception e){
                --i;
                System.out.println("i in catch - form try block is："+i);
                return --i;
            }
            finally{           
                System.out.println("i in finally - from try or catch block is："+i);
                --i;
                System.out.println("i in finally block is："+i);
                //return --i;
            }
}
运行结果：
=============NoException1==================
i in try block is：10
i in finally - from try or catch block is：9
i in finally block is：8
9
===============================
执行顺序：
   try中执行完return的语句后，不返回，执行finally块，finally块执行结束后，返回到try块中，返回i在try块中最后的值。
结论：try-catch都有return语句时，没有异常时，返回值是try中的return返回的。

3.try块中抛出异常，try、catch和finally中都有return语句
public static int WithException(){
            int i=10;
            try{
                System.out.println("i in try block is："+i);
                i = i/0;
                return --i;
            }
            catch(Exception e){
                System.out.println("i in catch - form try block is："+i);
                --i;
                System.out.println("i in catch block is："+i);
                return --i;
            }
            finally{           
                System.out.println("i in finally - from try or catch block is--"+i);
                --i;
                System.out.println("i in finally block is--"+i);
                return --i;
            }
}
执行结果：
=============WithException==================
i in try block is：10
i in catch - form try block is：10
i in catch block is：9
i in finally - from try or catch block is--8
i in finally block is--7
6
===============================
执行顺序：
   抛出异常后，执行catch块，在catch块的return的--i执行完后，并不直接返回而是执行finally，因finally中有return语句，所以，执行，返回结果6。
结论：
   try块中抛出异常，try、catch和finally中都有return语句，返回值是finally中的return。

4.try块中抛出异常，try和catch中都有return语句
public static int WithException1(){
            int i=10;
            try{
                System.out.println("i in try block is："+i);
                i=i/0;
                return --i;
            }catch(Exception e){
                System.out.println("i in catch - form try block is："+i);           
                return --i;
            }finally{
                                                                                                                                                                      
                System.out.println("i in finally - from try or catch block is："+i);
                --i;
                System.out.println("i in finally block is："+i);
                //return i;
            }
}
执行结果：
=============WithException1==================
i in try block is：10
i in catch - form try block is：10
i in finally - from try or catch block is：9
i in finally block is：8
9
===============================
执行顺序：
   抛出异常后，执行catch块，执行完finally语句后，依旧返回catch中的执行return语句后的值，而不是finally中修改的值。
结论：
   返回的catch中return值。

5.try、catch中都出现异常，在finally中有返回
public static int WithException2(){
            int i=10;
            try{
                System.out.println("i in try block is："+i);
                i=i/0;
                return --i;
            }
            catch(Exception e){
                System.out.println("i in catch - form try block is："+i);
                int j = i/0;
                return --i;
            }
            finally{
                                                                                       
                System.out.println("i in finally - from try or catch block is："+i);
                --i;
                --i;
                System.out.println("i in finally block is："+i);
                return --i;
}
执行结果：
=============WithException2==================
i in try block is：10
i in catch - form try block is：10
i in finally - from try or catch block is：10
i in finally block is：8
7
===============================
执行顺序：   
   try块中出现异常到catch，catch中出现异常到finally，finally中执行到return语句返回，不检查异常。
结论：
   返回finally中return值。

6、只在函数最后出现return语句
public static int WithException3(){
            int i=10;
            try{
                System.out.println("i in try block is："+i);
                i=i/0;
                //return --i;
            }
            catch(Exception e){
                System.out.println("i in catch - form try block is："+i);
                //int j = i/0;
                //return --i;
            }
            finally{
                                                                          
                System.out.println("i in finally - from try or catch block is："+i);
                --i;
                --i;
                System.out.println("i in finally block is："+i);
                //return --i;
            }
            return --i;
}
执行结果：
=============WithException3==================
i in try block is：10
i in catch - form try block is：10
i in finally - from try or catch block is：10
i in finally block is：8
7
===============================

总体结论：
结论一：
   return语句并不是函数的最终出口，如果有finally语句，这在return之后还会执行finally（return的值会暂存在栈里面，等待finally执行后再返回）
结论二：
   finally里面不建议放return语句，根据需要，return语句可以放在try和catch里面和函数的最后。可行的做法有四：
   （1）return语句只在函数最后出现一次。
   （2）return语句仅在try和catch里面都出现。
   （3）return语句仅在try和函数的最后都出现。
   （4）return语句仅在catch和函数的最后都出现。
   注意，除此之外的其他做法都是不可行的，编译器会报错。
