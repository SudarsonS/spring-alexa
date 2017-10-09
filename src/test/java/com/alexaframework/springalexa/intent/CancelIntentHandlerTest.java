package com.alexaframework.springalexa.intent;

import com.alexaframework.springalexa.config.AlexaConfiguration;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.SimpleCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CancelIntentHandlerTest {

    @InjectMocks
    private CancelIntentHandler handler;

    @Mock
    private AlexaConfiguration configuration;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldReturnTrueWhenTheIntentNameIsAmazonCancelIntent() throws Exception {

        boolean canHandle = handler.canHandle("AMAZON.CancelIntent");

        assertTrue(canHandle);
    }

    @Test
    public void shouldReturnFalseWhenTheIntentNameIsNotAmazonCancelIntent() throws Exception {

        boolean canHandle = handler.canHandle("AMAZON.CancelIntent1");

        assertFalse(canHandle);
    }

    @Test
    public void shouldReturnConfiguredMessage() throws Exception {
        when(configuration.getProperty(any(), any())).thenReturn("Thank you");
        Session session = Session.builder().withSessionId("123").build();
        Intent intent = Intent.builder().withName("cancel").build();

        SpeechletResponse response = handler.handle(session, intent);

        assertThat(((SimpleCard) response.getCard()).getContent(), is("Thank you"));
    }
}