package test.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.RequestVar
import net.liftweb.util.ValueCell
import net.liftweb.http.WiringUI
import net.liftweb.util.Helpers


object ColorSnippet {
  val colors = "red" :: "green" :: "blue" :: Nil
  object Current extends RequestVar[ValueCell[String]](ValueCell(colors(0)))

  def render1 = "tbody *" #> {
    "tr" #> colors.map { color =>
      "tr [class]" #> (if (color == Current.currentValue._1) "selected" else "") &
      "@color" #> color
    }
  }
  
  def render2 = "tbody *" #> {
    "tr" #> colors.map { color =>
      WiringUI(Current) { currentColor => 
        "tr [class]" #> (if (color == Current.currentValue._1) "selected" else "") &
        "@color" #> color
      }
    }
  }
}
