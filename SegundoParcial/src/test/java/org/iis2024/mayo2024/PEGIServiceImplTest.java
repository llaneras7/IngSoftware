package org.iis2024.mayo2024;

import org.junit.jupiter.api.Test;

import static org.iis2024.mayo2024.PEGIService.PEGI_CODE.PEGI12;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PEGIServiceImplTest {
    PEGIServiceImpl pegiService;
    @Test
    public void testage12() {
        pegiService = new PEGIServiceImpl();

        assertFalse(pegiService.validateAge(PEGI12, 11));
        assertTrue(pegiService.validateAge(PEGI12, 12));
        assertTrue(pegiService.validateAge(PEGI12, 13));

        pegiService = null;
    }
}
