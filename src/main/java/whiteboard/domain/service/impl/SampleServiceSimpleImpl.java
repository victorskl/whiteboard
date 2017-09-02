package whiteboard.domain.service.impl;

import whiteboard.domain.model.Sample;
import whiteboard.domain.service.SampleService;

public class SampleServiceSimpleImpl implements SampleService {
    @Override
    public Sample getSample() {
        return new Sample();
    }
}
