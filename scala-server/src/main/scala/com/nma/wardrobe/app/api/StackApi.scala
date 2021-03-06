package com.nma.wardrobe.app.api

import com.nma.wardrobe.app.model.{Shelf, Stack}
import java.io.{File, InputStream}

import com.nma.wardrobe.StackDao
import org.scalatra.{NotImplemented, ScalatraServlet, TypedParamSupport}
import org.scalatra.swagger._
import org.json4s._
import org.json4s.JsonDSL._
import org.scalatra.json.{JValueResult, JacksonJsonSupport}
import org.scalatra.servlet.{FileUploadSupport, MultipartConfig, SizeConstraintExceededException}

import scala.collection.JavaConverters._

class StackApi (implicit val swagger: Swagger) extends ScalatraServlet 
    with FileUploadSupport
    with JacksonJsonSupport
    with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "StackApi"
  override protected val applicationName: Option[String] = Some("Stack")
  val implementation = new StackDao();

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }


  val getStackOperation = (apiOperation[Unit]("getStack")
    summary "Get stacks"
  )

  get("/stack/", operation(getStackOperation)) {
    println("get all stacks")
    // implementation.retrieveStacks()

    val stack = new Stack(
      1,
      "bob",
      "admin",
      List[Shelf]()
    )

    List[Stack](stack)
  }

  val createStackOperation = (apiOperation[Unit]("createStack")
      summary "Create stack"
      parameters(bodyParam[Stack]("body").description(""))
  )

  post("/stack/",operation(createStackOperation)) {
    val body = bodyParam[Stack]("body").description("")
    
    println("body: " + body)
    NotImplemented
  }

  

  val stackStackIdGetOperation = (apiOperation[Stack]("stackStackIdGet")
      summary ""
      parameters(pathParam[Long]("stackId").description(""))
  )

  get("/stack/\\{stackId\\}",operation(stackStackIdGetOperation)) {
    
    
    
      val stackId = params.getOrElse("stackId", halt(400))
                

    
    println("stackId: " + stackId)
  
  }

  

  val stackStackIdPutOperation = (apiOperation[Unit]("stackStackIdPut")
      summary ""
      parameters(pathParam[Long]("stackId").description(""))
  )

  put("/stack/\\{stackId\\}",operation(stackStackIdPutOperation)) {
    
    
    
      val stackId = params.getOrElse("stackId", halt(400))
                

    
    println("stackId: " + stackId)
    NotImplemented
  }

  

  val stackStackIdDeleteOperation = (apiOperation[Unit]("stackStackIdDelete")
      summary ""
      parameters(pathParam[Long]("stackId").description(""))
  )

  delete("/stack/\\{stackId\\}",operation(stackStackIdDeleteOperation)) {
    
    
    
      val stackId = params.getOrElse("stackId", halt(400))
                

    
    println("stackId: " + stackId)
    NotImplemented
  }

}