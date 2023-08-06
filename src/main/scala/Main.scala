import javax.lang.model.element.VariableElement
import scala.collection.mutable._
class point(val x:Int, val y:Int){
    var zc=0
    var xc=x
    var yc=y
    def this( a:Int,b:Int,c:Int){
        this(a,b)
        this.zc=c
    }

    def move( dx:Int, dy:Int){
        xc += dx
        yc += dy
        println("x is:"+xc+" and y is: "+yc)


    }
    def move(dx:Int,dy:Int,dz:Int){
        xc += dx
        yc +=dy
        zc += dz
        println("x is:"+xc+" and y is: "+yc+"  z is :"+zc)

    }
}
object demo {
def main(args: Array[String]) {
val pt=new point(10,20)
pt.move(5,5)
val pt2=new point(50,60,70)
pt2.move(10,10,10)

}}

