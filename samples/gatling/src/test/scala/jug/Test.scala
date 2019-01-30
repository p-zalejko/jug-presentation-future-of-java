package jug

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Test extends Simulation {

  private val scn8080 = createScenario()
  private val scn8081 = createScenario()
  private val scn8082 = createScenario()
  private val scn8083 = createScenario()
  private val scn8084 = createScenario()
  private val scn8085 = createScenario()
  private val scn8086 = createScenario()
  private val scn8087 = createScenario()
  private val scn8088 = createScenario()
  private val scn8089 = createScenario()
  private val scn8090 = createScenario()
  private val scn8091 = createScenario()

  val duration = 10
  val rate = 1

  setUp(
    scn8080.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8080)),
    scn8081.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8081)),
    scn8082.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8082)),
    scn8083.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8083)),
    scn8084.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8084)),
    scn8085.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8085)),
    scn8086.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8086)),
    scn8087.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8087)),
    scn8088.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8088)),
    scn8089.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8089)),
    scn8090.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8090)),
    scn8091.inject(constantUsersPerSec(rate) during (duration)).protocols(createHttp(8091))
  )

  private def createHttp(port: Int) = {
    http.baseUrl("http://localhost:" + port)
  }

  private def createScenario() = {
    scenario("Server_" + UUID.randomUUID().toString)
      .exec(http("GET")
        .get("/samples")
        .check(status is 200)
        .check(bodyBytes.transform(_.length > 1).is(true))
      )
  }
}
