package ru.neoflex.vacation_pay_calculator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.vacation_pay_calculator.service.days.CalculationOfDaysServiceImpl;
import ru.neoflex.vacation_pay_calculator.service.vacation.VacationPayCalculatorServiceImpl;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class VacationPayCalculationTest {

    private VacationPayCalculatorServiceImpl vacationPayCalculatorService;
    private CalculationOfDaysServiceImpl daysCalculationService;

    private final BigDecimal testAverageSalaryPerYear = new BigDecimal("40000.00");
    private final int testVacationDays = 30;

    @BeforeEach
    void init() {
        log.info("start");
        vacationPayCalculatorService = new VacationPayCalculatorServiceImpl();
        daysCalculationService = new CalculationOfDaysServiceImpl();
    }

    @AfterEach
    public void teardown() {
        log.info("end");
        vacationPayCalculatorService = null;
        daysCalculationService = null;
    }

    @Test
    void calculationOfVacationPayForEmployeeUsingSimpleQueryTest() throws Exception {

        BigDecimal actual = vacationPayCalculatorService.getVacationPayCalculation(testAverageSalaryPerYear, testVacationDays);
        assertEquals(BigDecimal.valueOf(35631.7), actual.stripTrailingZeros());
    }

    @Test
    void calculationOfVacationPayForEmployeeUsingQueryWithDateTest() throws Exception {

        LocalDate testStartVacationDate = LocalDate.of(2024, 1, 30);

        int testPaidVacationDays = daysCalculationService.calculatePaidDays(testVacationDays, testStartVacationDate);
        BigDecimal actual = vacationPayCalculatorService.getVacationPayCalculation(testAverageSalaryPerYear, testPaidVacationDays);
        assertEquals(BigDecimal.valueOf(24941.99), actual);
    }
}
