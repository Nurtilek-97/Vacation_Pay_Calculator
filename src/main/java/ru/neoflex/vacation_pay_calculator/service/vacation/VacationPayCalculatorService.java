package ru.neoflex.vacation_pay_calculator.service.vacation;

import java.math.BigDecimal;

public interface VacationPayCalculatorService {

    BigDecimal getVacationPayCalculation(BigDecimal averageSalaryPerYear,
                                         int vacationDays);
}
