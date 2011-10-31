package bootstrap.liftweb

import net.liftweb._
import net.liftweb.common._
import net.liftweb.http._
import net.liftweb.util._
import net.liftweb.sitemap._

class Boot {

  def boot {
    
    // where to look for snippets
    LiftRules.addToPackages("test")
    
    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemap)
    
    // Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
     
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)
      
     // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    
    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))      
  }

	def sitemap = SiteMap (
	    Menu.i("Home") / "index", 
	    Menu.i("Assets") / "assets", 
	    Menu.i("Models") / "models" 
	)
}