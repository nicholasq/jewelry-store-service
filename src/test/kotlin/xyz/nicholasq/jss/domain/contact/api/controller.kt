package xyz.nicholasq.jss.domain.contact.api

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import jakarta.inject.Inject
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.*
import xyz.nicholasq.jss.data.FirestoreEmulator
import xyz.nicholasq.jss.domain.contact.Contact
import xyz.nicholasq.jss.domain.contact.CreateContactCommand
import xyz.nicholasq.jss.infrastructure.data.FirestoreCollectionConfiguration
import xyz.nicholasq.jss.infrastructure.data.FirestoreDb
import xyz.nicholasq.jss.infrastructure.service.Dto
import java.time.Clock
import java.time.ZoneId
import java.time.ZonedDateTime

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactControllerTest {

    @Inject
    lateinit var firestoreDb: FirestoreDb

    @Inject
    lateinit var firestoreCollectionConfig: FirestoreCollectionConfiguration

    @Inject
    lateinit var firestoreEmulator: FirestoreEmulator

    private val clock = Clock.fixed(ZonedDateTime.now().toInstant(), ZoneId.systemDefault())

    @BeforeAll
    fun init() {
        firestoreEmulator.startFirestoreDb()
    }

    @AfterAll
    fun cleanup() {
        firestoreEmulator.closeFirestoreDb()
    }

    private var createdDto: Dto<String>? = null

    @Test
    @Order(1)
    fun `test creating contact`(spec: RequestSpecification) {
        val createContactCommand = CreateContactCommand(
            firstName = "John",
            lastName = "Doe",
            email = "john@email.com",
            dateOfBirth = ZonedDateTime.now(clock),
            phone = "6028675309",
            address = "123 Main St Mesa, AZ 85206",
            company = "Acme Inc",
            jobTitle = "Manager",
            notes = "Great customer"
        )

        val response = spec.`when`()
            .body(createContactCommand)
            .contentType("application/json")
            .post("/api/v1/contacts/")
            .then()
            .statusCode(201)
            .body("firstName", equalTo("John"))
            .body("lastName", equalTo("Doe"))
            .body("email", equalTo("john@email.com"))
            .body("dateOfBirth", equalTo(ZonedDateTime.now(clock).toString()))
            .body("phone", equalTo("6028675309"))
            .body("address", equalTo("123 Main St Mesa, AZ 85206"))
            .body("company", equalTo("Acme Inc"))
            .body("jobTitle", equalTo("Manager"))
            .body("notes", equalTo("Great customer"))
            .extract()

        createdDto = response.`as`(Contact::class.java) as Dto<String>
    }

    @Test
    fun `test finding contact`(spec: RequestSpecification) {
        spec.`when`()
            .get("/api/v1/contacts/${createdDto?.id}")
            .then()
            .statusCode(200)
            .body("firstName", equalTo("John"))
    }
}
