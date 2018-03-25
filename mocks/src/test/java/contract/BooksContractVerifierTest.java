package contract;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static pl.dk.soa.service.Hosts.MOCK_HOST;

class BooksContractVerifierTest {

	static final String HOST = MOCK_HOST;

	@Test
	void validateEcuAccPedalPosition() {
		// given:
			RequestSpecification request = given()
                    .contentType(JSON)
                    .body(new JSONObject()
                            .put("title", "Ogniem i Mieczem")
							.put("author", "Sienkiewicz")
                            .toString()
                    );
		// when:
			Response response = request.when()
					.post(HOST + "/library/books");

		// then:
			response.then()
					.statusCode(ACCEPTED.value())
					.contentType(JSON);
	}
}
