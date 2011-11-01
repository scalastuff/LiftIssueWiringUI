package test.snippet

import xml.Text
import net.liftweb.http.RequestVar
import net.liftweb.http.SessionVar
import net.liftweb.http.SHtml
import net.liftweb.http.WiringUI
import net.liftweb.http.js.JsCmds.Noop
import net.liftweb.util.Helpers
import net.liftweb.util.Helpers._
import net.liftweb.util.ValueCell


object ColorSnippet {
  val allColors = "red" :: "green" :: "blue" :: "purple" :: "yellow" :: "orange" :: Nil
  
  object Colors extends RequestVar[ValueCell[Seq[String]]](ValueCell(allColors))
  object Current extends RequestVar[ValueCell[String]](ValueCell(allColors(0)))

  def search = "input" #> SHtml.ajaxText("", false, { (value : String) =>
    val colors = if (value.trim.isEmpty) allColors else allColors.filter(_.contains(value.trim))
    Colors.get.atomicUpdate(_ => colors)
    Noop
  })    
  
  def table = WiringUI(Colors) { colors => 
    "tbody *" #> {
      "tr" #> colors.map { color =>
        "@color" #> SHtml.a(() => { Current.is.atomicUpdate(_ => color); Noop }, Text(color))
      }
    }
  }

  def currentColor = "*" #> WiringUI.asText(Current)
}
