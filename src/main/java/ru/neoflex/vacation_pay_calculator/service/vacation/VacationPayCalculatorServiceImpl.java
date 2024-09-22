package ru.neoflex.vacation_pay_calculator.service.vacation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Component
@RequiredArgsConstructor
public class VacationPayCalculatorServiceImpl implements VacationPayCalculatorService {

    private static final double AVERAGE_NUMBER_DAYS_IN_MOUNT = 29.3;

    private static final double NDFL_PERCENT = 0.13;

    /**
     * Расчёт отпускных сотрудников
     * @param averageSalaryPerYear средняя зарплата за один год
     * @param vacationDays         количество дней отпуска
     * @return сумма отпускных
     */
    @Override
    public BigDecimal getVacationPayCalculation(BigDecimal averageSalaryPerYear,
                                                int vacationDays) {

        BigDecimal averageEarningsPerDay = averageSalaryPerYear.divide(BigDecimal.valueOf(AVERAGE_NUMBER_DAYS_IN_MOUNT), 2, RoundingMode.HALF_EVEN);

        BigDecimal totalPayWithoutNDFL = averageEarningsPerDay.multiply(BigDecimal.valueOf(vacationDays));

        BigDecimal amountNDFL = totalPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL_PERCENT)).setScale(0, RoundingMode.HALF_UP);

        BigDecimal totalPay = totalPayWithoutNDFL.subtract(amountNDFL);

        return totalPay;
    }
}
