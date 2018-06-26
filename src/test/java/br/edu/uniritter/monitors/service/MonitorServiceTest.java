package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.Monitor;
import br.edu.uniritter.monitors.repository.MonitorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class MonitorServiceTest {

    @Mock
    private MonitorRepository monitorRepository;

    @InjectMocks
    private MonitorService monitorService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCallThresholdRepositoryFindAll() {

        // given the mocked monitors
        List<Monitor> monitors = new ArrayList<Monitor>();
        monitors.add(new Monitor(1L, "some-origin", Metric.valueOf("MEMORY_USAGE"), Rule.valueOf("GREATER_THAN"), 100L));

        // when I call service All
        Mockito.when(monitorRepository.findAll()).thenReturn(monitors);

        List<Monitor> thresholdsFounded = monitorService.all();

        // then I expect all monitors
        assertSame(monitors, thresholdsFounded);

    }

    @Test
    public void shouldCallThresholdRepositoryFindOneByOriginAndMetric() {

        // given the mocked monitor
        Monitor monitor = new Monitor(1L, "some-origin", Metric.MEMORY_USAGE, Rule.GREATER_THAN, 100L);


        // when I call service findOneByOriginAndMetric
        Mockito.when(monitorRepository.findOneByOriginAndMetric("some-origin", Metric.MEMORY_USAGE)).thenReturn(monitor);

        Monitor monitorFounded = monitorService.findOneByOriginAndMetric("some-origin", Metric.MEMORY_USAGE);

        // then I expect the monitor
        assertEquals(monitor.getId(), monitorFounded.getId());
    }


}
