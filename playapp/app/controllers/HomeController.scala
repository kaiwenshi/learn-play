package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Customer
import play.api.Play.current
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def welcome = Action {
    Ok(views.html.home())
  }

  def about = Action {
    Ok(views.html.about())
  }

  def handleForm2 = Action { implicit request =>
    Ok(views.html.about())
  }

  def handleForm = Action { implicit request =>
    val loginRequest = loginForm.bindFromRequest.get
    Ok("Username was "+loginRequest.username)
  }

  def customer = Action {
    Ok(views.html.customer())
  }

  def createCustomer = Action { implicit request =>
    customerForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.customer()),
      customer => Ok(s"Customer ${customer.name} created successfully"))
  }

  def customerForm = Form(mapping("Customer Name" -> text,
    "Credit Limit" -> text)(Customer.apply)(Customer.unapply))

  def loginForm = Form(mapping("username" -> text)
      (LoginRequest.apply)(LoginRequest.unapply))

  case class LoginRequest(username:String)

}
