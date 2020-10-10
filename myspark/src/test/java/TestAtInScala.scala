object TestAtInScala {

  def main(args: Array[String]): Unit = {
    val d@(c@Some(a), Some(b)) = (Some(1), Some(2))

    println(a)
    println(b)
    println(c)
    println(d)

  }

}
