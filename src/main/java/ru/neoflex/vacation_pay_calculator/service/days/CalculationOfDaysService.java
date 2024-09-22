package ru.neoflex.vacation_pay_calculator.service.days;

import java.time.LocalDate;

public interface CalculationOfDaysService {

    int calculatePaidDays(int vacationDays,
                          LocalDate startVacationDate);
}
