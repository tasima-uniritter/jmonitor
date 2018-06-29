package br.edu.uniritter.monitors.dto;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ApiErrorDTOTest {

    @Test
    public void testApiError(){
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(HttpStatus.BAD_REQUEST, "Validation error", "Invalid parameters");
        assertEquals("Validation error", apiErrorDTO.getMessage());
    }

    @Test
    public void testApiErrorList(){
        List<String> errorList = new ArrayList<String>();
        errorList.add("Invalid parameter Origin");

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(HttpStatus.BAD_REQUEST, "Validation error", errorList);
        assertSame(errorList, apiErrorDTO.getErrors());
    }
}
