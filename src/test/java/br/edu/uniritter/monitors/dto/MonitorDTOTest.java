package br.edu.uniritter.monitors.dto;

import br.edu.uniritter.monitors.entity.Monitor;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.Assert.assertEquals;

public class MonitorDTOTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldConvertToEntity() {
        Monitor monitor = random(Monitor.class);
        MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);

        assertEquals(monitorDTO.getOrigin(), monitor.getOrigin());
        assertEquals(monitorDTO.getMetric(), monitor.getMetric());
        assertEquals(monitorDTO.getThreshold(), monitor.getThreshold());
        assertEquals(monitorDTO.getRule(), monitor.getRule());
    }

    @Test
    public void shouldConvertToDTO() {
        MonitorDTO monitorDTO = random(MonitorDTO.class);
        Monitor monitor = modelMapper.map(monitorDTO, Monitor.class);

        assertEquals(monitor.getOrigin(), monitorDTO.getOrigin());
        assertEquals(monitor.getMetric(), monitorDTO.getMetric());
        assertEquals(monitor.getThreshold(), monitorDTO.getThreshold());
        assertEquals(monitor.getRule(), monitorDTO.getRule());
    }
}
