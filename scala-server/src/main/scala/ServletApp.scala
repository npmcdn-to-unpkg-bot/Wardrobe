package io.swagger.app

import _root_.akka.actor.ActorSystem
import org.scalatra.swagger.{ApiInfo, Swagger, SwaggerWithAuth}
import org.scalatra.swagger.{JacksonSwaggerBase, Swagger}
import org.scalatra.ScalatraServlet
import org.json4s.{DefaultFormats, Formats, JValue, JsonInput}

class ResourcesApp(implicit protected val system: ActorSystem, val swagger: SwaggerApp)
  extends ScalatraServlet with JacksonSwaggerBase {
  before() {
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  protected def buildFullUrl(path: String) = if (path.startsWith("http")) path else {
   val port = request.getServerPort
   val h = request.getServerName
   val prot = if (port == 443) "https" else "http"
   val (proto, host) = if (port != 80 && port != 443) ("http", h+":"+port.toString) else (prot, h)
   "%s://%s%s%s".format(
     proto,
     host,
     request.getContextPath,
     path)
  }
}

class SwaggerApp extends Swagger(apiInfo = ApiSwagger.apiInfo, apiVersion = "1.0", swaggerVersion = "1.2")

object ApiSwagger {
  val apiInfo = ApiInfo(
    """Wardrobe""",
    """Simple UI to promote .war files between S3 locations""",
    """http://swagger.io""",
    """apiteam@swagger.io""",
    """Apache 2.0""",
    """http://www.apache.org/licenses/LICENSE-2.0.html""")
}