import com.nma.wardrobe.app.api._
import akka.actor.ActorSystem
import _root_.io.swagger.app.{ResourcesApp, SwaggerApp}
import javax.servlet.ServletContext

import com.nma.wardrobe.app.web.IndexServlet
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {
  implicit val swagger = new SwaggerApp

  override def init(context: ServletContext) {
    implicit val system = ActorSystem("appActorSystem")
    try {
      context mount (new UserApi, "/User/*")
      context mount (new ShelfApi, "/Shelf/*")
      context mount (new StackApi, "/Stack/*")

      context mount (new ResourcesApp, "/api-docs/*")

      context mount (new IndexServlet, "/")
    } catch {
      case e: Throwable => e.printStackTrace()
    }
  }
}