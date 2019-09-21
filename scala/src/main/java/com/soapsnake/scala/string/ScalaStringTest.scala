package com.soapsnake.scala.string

object ScalaStringTest {

  def main(args: Array[String]): Unit = {

    val s1 = "string1";
    val s2 = "string1";

    println(s1 == s2);


    val foo =
      """this is
         mutiply line
         string srt
        """.stripMargin('#').replaceAll("\n", " ")
    println(foo)


    val name = "kevin"
    val age = 18
    val xingge = "buhao"

    //关键是这个s,没有的话就不会替换变量名称
    println(s"$name is a good man also his age is $age but his xingge is $xingge")

    println(s"you are 33 years old : ${age == 33}")

//    val upper = "hello, world".map(c => c.toUpper)
//    println(upper)

    val some = for (c <- "hello world") yield c.toUpper
    println(some)




  }

}
