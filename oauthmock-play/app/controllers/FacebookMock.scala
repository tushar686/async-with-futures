package controllers

import play.api._
import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.libs.ws._
import config.ConfigBlocking.executionContext
import reactive.Scheduler
import scala.concurrent.duration.DurationInt
import scala.util.Random

object FacebookMock extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def facebookSync(access_token : String) = Action {
  	if (access_token == null) {
      BadRequest("access token must be provided")
    } 
  	val mockResponse = getMockResponse
  	
  	Future {
  		println(s"Sending Response: $mockResponse")
  	}
  	
    Ok(mockResponse)
  }
  
  def facebookAsync(access_token : String) = Action.async {
  	if (access_token == null) {
      BadRequest("access token must be provided")
    } 
    val mockResponseF = getMockResponseFuture;

    mockResponseF.map { mockResponse =>
      println(s"Sending Response: $mockResponse")
      Ok(mockResponse)
    }
  }
  
  def facebookAsyncScheduler(access_token : String) = Action.async {
  	if (access_token == null) {
      BadRequest("access token must be provided")
    } 
    val mockResponseF = getMockResponseScheduler;

    mockResponseF.map { mockResponse =>
      Future {println(s"Sending Response: $mockResponse") }
      Ok(mockResponse)
    }
  }
  
  def getMockResponseFuture : Future[String] = Future {
	  		val result = getMockResponse
    		Thread.sleep(1000)		
    		result
  }
  
  def getMockResponseScheduler : Future[String] = {

	  		Scheduler.asFuture(DurationInt(1000).millis) {
	  			val result = getMockResponse
	  			result
	  		}
  }

  private def getMockResponse: String = {
    val result =
        """{\"id\": \"719883168076083\"," + "\"email\": \"test@dummy.com\"," + "\"first_name\": \"xyz\",
            \"gender\": \"male\"," + "\"last_name\": \"xyz\",
            \"link\": \"https://www.facebook.com/app_scoped_user_id/719883168076083/\"," + "\"locale\": \"en_US\",
            "\"name\": \"xyz\"," + "\"timezone\": 5.5," + "\"updated_time\": \"2012-10-11T03:12:22+0000\",
            "\"verified\": true}"""
    result
  }
  
  

}