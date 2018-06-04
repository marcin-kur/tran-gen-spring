package com.example.generator.services.parsers;

import com.example.generator.model.InputParameters;
import com.example.generator.model.RequestParameters;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParametersCreatorTest {

    @Test
    public void shouldCreateInputParameters() {
        // given
        IntegerRangeParser integerRangeParser = new IntegerRangeParser();
        TimestampRangeParser timestampRangeParser = new TimestampRangeParser();
        IntegerParser integerParser = new IntegerParser();
        InputParametersCreator inputParametersCreator = new InputParametersCreator(integerRangeParser, timestampRangeParser, integerParser);

        RequestParameters requestParameters = new RequestParameters(
                Optional.of("1:20"),
                Optional.of("1:20"),
                Optional.of("1:20"),
                Optional.of("1:20"),
                Optional.of("20")
        );

        // when
        InputParameters inputParameters = inputParametersCreator.create(requestParameters);

        // then
        assertThat(inputParameters.getEventsCount());
    }
}
