package br.edu.uniritter.monitors.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import pl.pojo.tester.api.assertion.Method;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class ApplicationConfigTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Dotenv dotenv;

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPassAllPojoTests() {
        // given
        final Class<?> classUnderTest = ApplicationConfig.class;

        // when
        final Throwable result = catchThrowable(
            () -> assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented()
        );

        // then
        assertNull(result);
    }

    @Test
    public void shouldReturnPasswordSetOnEnvironmentVariable() {
        // given I have an applicationConfig object
        // and AMQP_SERVICE_PASSWORD environment variable is set
        Mockito.when(dotenv.get("AMQP_SERVICE_PASSWORD")).thenReturn("value");

        // when I getAmqpPassword
        String password = applicationConfig.getAmqpPassword();

        // I expect password is equal to AMQP_SERVICE_PASSWORD
        assertEquals("value", password);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPasswordSetOnEnvironmentVariableDoesEmpty() {
        // given I have an applicationConfig object
        // and AMQP_SERVICE_PASSWORD environment variable is set
        Mockito.when(dotenv.get("AMQP_SERVICE_PASSWORD")).thenReturn(null);

        // when I getAmqpPassword
        applicationConfig.getAmqpPassword();

        // I excpect IllegalArgumentException
    }
}
