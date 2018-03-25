package contract;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.http.HttpStatus.OK;
import static pl.dk.soa.service.Hosts.MOCK_HOST;

class GpsContractVerifierTest {

	static final String HOST = MOCK_HOST;

	@Test
	void validateGps() {
		// when:
		Response response = when()
				.get(HOST + "/gps/my-coordinates");

		// then:
		response.then()
				.statusCode(OK.value())
				.contentType(JSON)
				.body("lat", allOf(greaterThanOrEqualTo(-180f), lessThanOrEqualTo(180f)))
				.body("lng", allOf(greaterThanOrEqualTo(-180f), lessThanOrEqualTo(180f)));
	}

}
